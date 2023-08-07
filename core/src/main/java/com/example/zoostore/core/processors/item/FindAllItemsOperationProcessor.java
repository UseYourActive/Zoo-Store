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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindAllItemsOperationProcessor implements FindAllItemsOperation {
    private final ItemRepository itemRepository;
    private final TagRepository tagRepository;

    @Override
    public FindAllItemsResponse process(FindAllItemsRequest findAllItemsInput) {
        Tag tag = tagRepository.findById(findAllItemsInput.getTagId())
                .orElseThrow(TagNotFoundInRepositoryException::new);

        PageRequest pageable = PageRequest.of(findAllItemsInput.getPageNumber(), findAllItemsInput.getNumberOfItemsPerPage());
        Page<Item> allItems = itemRepository.findAllByArchivedAndTagsContaining(false, tag, pageable);

        return FindAllItemsResponse.builder()
                .items(allItems.stream()
                        .map(this::mapAllItemsToObject)
                        .toList())
                .build();
    }

    private FindAllItemsInRepo mapAllItemsToObject(Item item){
        return FindAllItemsInRepo.builder()
                .itemId(item.getId())
                .productName(item.getProductName())
                .description(item.getDescription())
                .vendorId(item.getVendor().getId())
                .tagIds(item.getTags().stream()
                        .map(Tag::getId)
                        .toList())
                .multimediaIds(item.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .toList())
                .isArchived(item.getArchived())
                .build();
    }
}
