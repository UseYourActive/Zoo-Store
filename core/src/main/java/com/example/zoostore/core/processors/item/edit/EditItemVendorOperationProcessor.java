package com.example.zoostore.core.processors.item.edit;

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
        Item itemFoundInRepository = itemRepository.findById(editItemVendorRequest.getVendorId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        Optional<Vendor> vendorOptional = vendorRepository.findVendorById(editItemVendorRequest.getItemId());

        Vendor vendor = vendorOptional
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        itemFoundInRepository.setVendor(vendor);

        Item savedItem = itemRepository.save(itemFoundInRepository);

        if(savedItem.getMultimedia().isEmpty()){
            return EditItemVendorResponse.builder()
                    .itemId(savedItem.getId())
                    .productName(savedItem.getProductName())
                    .isArchived(savedItem.isArchived())
                    .tagIds(savedItem.getTags().stream()
                            .map(Tag::getId)
                            .collect(Collectors.toSet()))
                    .vendorId(savedItem.getVendor().getId())
                    .description(savedItem.getDescription())
                    .build();
        }

        return EditItemVendorResponse.builder()
                .itemId(savedItem.getId())
                .productName(savedItem.getProductName())
                .isArchived(savedItem.isArchived())
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
