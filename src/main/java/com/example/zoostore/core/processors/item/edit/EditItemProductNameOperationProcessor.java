package com.example.zoostore.core.processors.item.edit;

import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameRequest;
import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameResponse;
import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameOperation;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.processors.item.GetMultimediaService;
import com.example.zoostore.core.processors.item.GetTagService;
import com.example.zoostore.core.processors.item.GetVendorService;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EditItemProductNameOperationProcessor implements EditItemProductNameOperation {
    private final ItemRepository itemRepository;
    private final GetTagService getTagService;
    private final GetVendorService getVendorService;
    private final GetMultimediaService getMultimediaService;

    @Override
    public EditItemProductNameResponse process(EditItemProductNameRequest editItemProductNameRequest) {
        Item itemFoundInRepository = itemRepository.findById(editItemProductNameRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        itemFoundInRepository.setProductName(editItemProductNameRequest.getTitle());

        Item savedItem = itemRepository.save(itemFoundInRepository);

        return EditItemProductNameResponse.builder()
                .id(savedItem.getId())
                .title(savedItem.getProductName())
                .build();
    }
}
