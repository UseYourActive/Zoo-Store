package com.example.zoostore.core.processors.item.edit;

import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorRequest;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorResponse;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorOperation;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.core.processors.item.GetMultimediaService;
import com.example.zoostore.core.processors.item.GetTagService;
import com.example.zoostore.core.processors.item.GetVendorService;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EditItemVendorOperationProcessor implements EditItemVendorOperation {
    private final ItemRepository itemRepository;
    private final GetTagService getTagService;
    private final GetVendorService getVendorService;
    private final GetMultimediaService getMultimediaService;

    @Override
    public EditItemVendorResponse process(EditItemVendorRequest editItemVendorRequest) {
        Item itemFoundInRepository = itemRepository.findById(editItemVendorRequest.getId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        Optional<Vendor> vendorOptional = Optional.ofNullable(getVendorService.getVendorsByID(editItemVendorRequest.getVendor()));

        Vendor vendor = vendorOptional
                .orElseThrow(() -> new VendorNotFoundInRepositoryException(HttpStatus.NOT_FOUND));

        itemFoundInRepository.setVendor(vendor);

        Item savedItem = itemRepository.save(itemFoundInRepository);

        return EditItemVendorResponse.builder()
                .id(savedItem.getId())
                .vendor(savedItem.getVendor().getId())
                .build();
    }
}
