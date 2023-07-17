package com.example.zoostore.core.services.item.edit;

import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionRequest;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionResponse;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionService;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.core.services.item.GetMultimediaService;
import com.example.zoostore.core.services.item.GetTagService;
import com.example.zoostore.core.services.item.GetVendorService;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EditItemDescriptionServiceImpl implements EditItemDescriptionService {
    private final ItemRepository itemRepository;
    private final GetTagService getTagService;
    private final GetVendorService getVendorService;
    private final GetMultimediaService getMultimediaService;

    @Override
    public EditItemDescriptionResponse process(EditItemDescriptionRequest editItemDescriptionRequest) {
        Item itemFoundInRepository = findItemById(editItemDescriptionRequest.getId());

        itemFoundInRepository.setDescription(editItemDescriptionRequest.getDescription());

        Item savedItem = itemRepository.save(itemFoundInRepository);

        return EditItemDescriptionResponse.builder()
                .id(savedItem.getId())
                .description(savedItem.getDescription())
                .build();
    }

    private Item findItemById(UUID id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundInRepositoryException(HttpStatus.NOT_FOUND));
    }
}
