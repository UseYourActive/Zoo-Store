package com.example.zoostore.core.services.item.archive;

import com.example.zoostore.api.operations.item.archive.ArchiveItemRequest;
import com.example.zoostore.api.operations.item.archive.ArchiveItemResponse;
import com.example.zoostore.api.operations.item.archive.ArchiveItemService;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArchiveItemServiceImpl implements ArchiveItemService {
    private final ItemRepository itemRepository;

    @Override
    public ArchiveItemResponse process(ArchiveItemRequest request) throws VendorNotFoundInRepositoryException, TagNotFoundInRepositoryException, MultimediaNotFoundInRepositoryException, ItemNotFoundInRepositoryException {
        Item found = findItemById(request.getId());

        Item item = Item.builder()
                .id(found.getId())
                .tags(found.getTags())
                .productName(found.getProductName())
                .description(found.getDescription())
                .vendor(found.getVendor())
                .multimedia(found.getMultimedia()) // moje da nqma multimedii i da stane null pointer
                .archived(true)
                .build();

        Item save = itemRepository.save(item);

        return ArchiveItemResponse.builder()
                .id(save.getId())
                .vendor(save.getVendor().getId())
                .description(save.getDescription())
                //.multimedia(save.getMultimedia().stream().map(Multimedia::getId).collect(Collectors.toSet()))
                .title(save.getProductName())
                .tags(save.getTags().stream().map(Tag::getId).collect(Collectors.toSet()))
                .isArchived(true)
                .build();
    }

    private Item findItemById(UUID id) throws ItemNotFoundInRepositoryException {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundInRepositoryException(HttpStatus.NOT_FOUND));
    }
}
