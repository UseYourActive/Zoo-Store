package com.example.zoostore.core.processors.item.findall;

import com.example.zoostore.api.operations.item.findall.FindALlItemsInRepo;
import com.example.zoostore.api.operations.item.findall.FindAllItemsRequest;
import com.example.zoostore.api.operations.item.findall.FindAllItemsOperation;
import com.example.zoostore.api.operations.item.findall.FindAllItemsResponse;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        Pageable firstPageWithTwoElements = PageRequest.of(findAllItemsInput.getPageNumber(), findAllItemsInput.getNumberOfItemsPerPage());
        Page<Item> allItems = itemRepository.findAllByArchivedAndTagsContaining(false, tag, firstPageWithTwoElements);

        return  FindAllItemsResponse.builder()
                .items(allItems.stream()
                        .map(this::mapAllItemsToObject)
                        .collect(Collectors.toSet()))
                .build();
    }

    private FindALlItemsInRepo mapAllItemsToObject(Item item){
        return FindALlItemsInRepo.builder()
                .id(item.getId())
                .productName(item.getProductName())
                .description(item.getDescription())
                .vendorId(item.getVendor().getId())
                .tagIds(item.getTags().stream()
                        .map(Tag::getId)
                        .collect(Collectors.toSet()))
                .multimediaIds(item.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .collect(Collectors.toSet()))
                .isArchived(item.isArchived())
                .build();
    }
}
