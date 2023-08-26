package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.find.bytag.FindItemsByTagInRepo;
import com.example.zoostore.api.operations.item.find.bytag.FindItemsByTagOperation;
import com.example.zoostore.api.operations.item.find.bytag.FindItemsByTagRequest;
import com.example.zoostore.api.operations.item.find.bytag.FindItemsByTagResponse;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindItemsByTagOperationProcessor implements FindItemsByTagOperation {
    private final TagRepository tagRepository;
    private final ItemRepository itemRepository;

    @Override
    public FindItemsByTagResponse process(FindItemsByTagRequest findItemByTagRequest) {
        log.info("Starting find items by tag operation");

        Tag tag = this.tagRepository.findById(findItemByTagRequest.getTagId())
                .orElseThrow(TagNotFoundInRepositoryException::new);
        log.info("Tag found in repository with ID: {}", tag.getId());

        PageRequest pageable = PageRequest.of(findItemByTagRequest.getPageNumber(), findItemByTagRequest.getNumberOfItemsPerPage());
        Page<Item> items = this.itemRepository.findItemsByTagsContainingAndArchived(tag, false, pageable);
        log.info("Found {} items matching the tag with ID: {}", items.getTotalElements(), tag.getId());

        List<FindItemsByTagInRepo> mappedItems = items.stream()
                .map(this::mapToObject)
                .toList();
        log.info("Mapping items to response objects completed. Count: {}", mappedItems.size());

        FindItemsByTagResponse response = FindItemsByTagResponse.builder()
                .items(mappedItems)
                .build();
        log.info("Find items by tag operation completed");

        return response;
    }

    private FindItemsByTagInRepo mapToObject(Item item){
        return FindItemsByTagInRepo.builder()
                .itemId(item.getId())
                .isArchived(item.getArchived())
                .productName(item.getProductName())
                .vendorId(item.getVendor().getId())
                .description(item.getDescription())
                .tagIds(item.getTags().stream()
                        .map(Tag::getId)
                        .toList())
                .multimediaIds(item.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .toList())
                .build();
    }
}
