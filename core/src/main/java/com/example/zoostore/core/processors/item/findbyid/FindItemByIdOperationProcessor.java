package com.example.zoostore.core.processors.item.findbyid;

import com.example.zoostore.api.operations.item.findbyid.FindItemByIdOperation;
import com.example.zoostore.api.operations.item.findbyid.FindItemByIdRequest;
import com.example.zoostore.api.operations.item.findbyid.FindItemByIdResponse;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindItemByIdOperationProcessor implements FindItemByIdOperation {
    private final ItemRepository itemRepository;

    @Override
    public FindItemByIdResponse process(FindItemByIdRequest findItemByIdRequest) {
        Item item = this.itemRepository.findItemById(findItemByIdRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        if(item.getMultimedia().isEmpty()){
            return FindItemByIdResponse.builder()
                    .id(item.getId())
                    .productName(item.getProductName())
                    .description(item.getDescription())
                    .vendor(item.getVendor().getId())
                    .tags(item.getTags().stream()
                            .map(Tag::getId)
                            .collect(Collectors.toSet()))
                    .archived(item.isArchived())
                    .build();
        }

        return FindItemByIdResponse.builder()
                .id(item.getId())
                .productName(item.getProductName())
                .description(item.getDescription())
                .vendor(item.getVendor().getId())
                .tags(item.getTags().stream()
                        .map(Tag::getId)
                        .collect(Collectors.toSet()))
                .multimedia(item.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .collect(Collectors.toSet()))
                .archived(item.isArchived())
                .build();
    }
}
