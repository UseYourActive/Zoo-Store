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
        Item item = this.itemRepository.findById(findItemByIdRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

//        if(item.getMultimedia().isEmpty()){
//            return FindItemByIdResponse.builder()
//                    .itemId(item.getId())
//                    .productName(item.getProductName())
//                    .isArchived(item.isArchived())
//                    .tagIds(item.getTags().stream()
//                            .map(Tag::getId)
//                            .collect(Collectors.toSet()))
//                    .vendorId(item.getVendor().getId())
//                    .description(item.getDescription())
//                    .build();
//        }

        return FindItemByIdResponse.builder()
                .itemId(item.getId())
                .productName(item.getProductName())
                .isArchived(item.isArchived())
                .tagIds(item.getTags().stream()
                        .map(Tag::getId)
                        .collect(Collectors.toSet()))
                .vendorId(item.getVendor().getId())
                .multimediaIds(item.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .collect(Collectors.toSet()))
                .description(item.getDescription())
                .build();
    }
}
