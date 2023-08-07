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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindItemsByProductNameOperationProcessor implements FindItemsByProductNameOperation {
    private final ItemRepository itemRepository;

    @Override
    public FindItemsByProductNameResponse process(FindItemsByProductNameRequest findItemByProductNameRequest) {
        PageRequest pageable = PageRequest.of(findItemByProductNameRequest.getPageNumber(), findItemByProductNameRequest.getNumberOfItemsPerPage());
        Page<Item> items = itemRepository.findItemsByProductNameContainingAndArchived(findItemByProductNameRequest.getProductName(), false, pageable);

        return FindItemsByProductNameResponse.builder()
                .items(items.stream()
                        .map(this::mapToObject)
                        .toList())
                .build();
    }

    private FindItemsByProductNamesRepo mapToObject(Item item){
        return FindItemsByProductNamesRepo.builder()
                .itemId(item.getId())
                .productName(item.getProductName())
                .description(item.getDescription())
                .isArchived(item.getArchived())
                .multimediaIds(item.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .toList())
                .vendorId(item.getVendor().getId())
                .tagIds(item.getTags().stream()
                        .map(Tag::getId)
                        .toList())
                .build();
    }
}
