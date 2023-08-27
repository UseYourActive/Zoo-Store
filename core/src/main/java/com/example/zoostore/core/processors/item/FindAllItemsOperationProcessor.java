package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.find.all.FindAllItemsInRepo;
import com.example.zoostore.api.operations.item.find.all.FindAllItemsRequest;
import com.example.zoostore.api.operations.item.find.all.FindAllItemsOperation;
import com.example.zoostore.api.operations.item.find.all.FindAllItemsResponse;
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
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindAllItemsOperationProcessor implements FindAllItemsOperation {
    private final ItemRepository itemRepository;
    private final TagRepository tagRepository;

    @Override
    public FindAllItemsResponse process(FindAllItemsRequest findAllItemsInput) {
        log.info("Starting find all items operation");

        Tag tag = tagRepository.findById(UUID.fromString(findAllItemsInput.getTagId()))
                .orElseThrow(TagNotFoundInRepositoryException::new);
        log.info("Found tag for find all items operation. TagId: {}", tag.getId());

        PageRequest pageable = PageRequest.of(findAllItemsInput.getPageNumber(), findAllItemsInput.getNumberOfItemsPerPage());
        Page<Item> allItems = itemRepository.findAllByArchivedAndTagsContaining(false, tag, pageable);
        log.info("Found {} items with tag {}. Page number: {}", allItems.getTotalElements(), tag.getId(), pageable.getPageNumber());

        List<FindAllItemsInRepo> mappedItems = allItems.stream()
                .map(this::mapAllItemsToObject)
                .toList();
        log.info("Mapping items to response objects completed. Count: {}", mappedItems.size());

        FindAllItemsResponse response = FindAllItemsResponse.builder()
                .items(mappedItems)
                .build();
        log.info("Find all items operation completed");

        return response;
    }

    private FindAllItemsInRepo mapAllItemsToObject(Item item){
        List<String> multimediaIds = item.getMultimedia().stream()
                .map(multimedia -> String.valueOf(multimedia.getId()))
                .toList();

        List<String> tagIds = item.getTags().stream()
                .map(tag -> String.valueOf(tag.getId()))
                .toList();

        return FindAllItemsInRepo.builder()
                .itemId(String.valueOf(item.getId()))
                .productName(item.getProductName())
                .description(item.getDescription())
                .vendorId(String.valueOf(item.getVendor().getId()))
                .tagIds(tagIds)
                .multimediaIds(multimediaIds)
                .isArchived(item.getArchived())
                .build();
    }
}
