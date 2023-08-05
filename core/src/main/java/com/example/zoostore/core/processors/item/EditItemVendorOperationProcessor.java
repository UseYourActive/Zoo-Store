package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorRequest;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorResponse;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorOperation;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EditItemVendorOperationProcessor implements EditItemVendorOperation {
    private final ItemRepository itemRepository;
    private final VendorRepository vendorRepository;

    @Override
    public EditItemVendorResponse process(EditItemVendorRequest editItemVendorRequest) {
        Item itemFoundInRepository = itemRepository.findById(editItemVendorRequest.getItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        Vendor vendor = vendorRepository.findById(editItemVendorRequest.getVendorId())
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        itemFoundInRepository.setVendor(vendor);

        Item savedItem = itemRepository.save(itemFoundInRepository);

        return EditItemVendorResponse.builder()
                .itemId(savedItem.getId())
                .productName(savedItem.getProductName())
                .isArchived(savedItem.getArchived())
                .tagIds(savedItem.getTags().stream()
                        .map(Tag::getId)
                        .collect(Collectors.toSet()))
                .vendorId(savedItem.getVendor().getId())
                .multimediaIds(savedItem.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .collect(Collectors.toSet()))
                .description(savedItem.getDescription())
                .build();
    }
}
