package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.find.byproductname.FindItemsByProductNameOperation;
import com.example.zoostore.api.operations.item.find.byproductname.FindItemsByProductNameRequest;
import com.example.zoostore.api.operations.item.find.byproductname.FindItemsByProductNameResponse;
import com.example.zoostore.api.operations.item.find.byproductname.FindItemsByProductNamesRepo;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindItemsByProductNameOperationProcessor implements FindItemsByProductNameOperation {
    private final ItemRepository itemRepository;

    @Override
    public FindItemsByProductNameResponse process(FindItemsByProductNameRequest findItemByProductNameRequest) {
        log.info("Starting find items by product name operation");

        PageRequest pageable = PageRequest.of(findItemByProductNameRequest.getPageNumber(), findItemByProductNameRequest.getNumberOfItemsPerPage(), Sort.by("productName").ascending());

        //String concat = findItemByProductNameRequest.getProductName().concat("/i"); // makes it case insensitive
        List<Item> items = itemRepository.findAllByPartialProductName(findItemByProductNameRequest.getProductName(), pageable).getContent();
        log.info("Found {} items matching the product name '{}'", items.size(), findItemByProductNameRequest.getProductName());

        List<FindItemsByProductNamesRepo> mappedItems = items.stream()
                .map(this::mapToObject)
                .toList();
        log.info("Mapping items to response objects completed. Count: {}", mappedItems.size());

        FindItemsByProductNameResponse response = FindItemsByProductNameResponse.builder()
                .items(mappedItems)
                .build();
        log.info("Find items by product name operation completed");

        return response;
    }

    private FindItemsByProductNamesRepo mapToObject(Item item){
        List<String> multimediaIds = item.getMultimedia().stream()
                .map(multimedia -> String.valueOf(multimedia.getId()))
                .toList();

        List<String> tagIds = item.getTags().stream()
                .map(tag -> String.valueOf(tag.getId()))
                .toList();

        return FindItemsByProductNamesRepo.builder()
                .itemId(String.valueOf(item.getId()))
                .productName(item.getProductName())
                .description(item.getDescription())
                .isArchived(item.getArchived())
                .multimediaIds(multimediaIds)
                .vendorId(String.valueOf(item.getVendor().getId()))
                .tagIds(tagIds)
                .build();
    }
}
