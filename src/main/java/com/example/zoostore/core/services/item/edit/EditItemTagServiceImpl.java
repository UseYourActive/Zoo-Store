package com.example.zoostore.core.services.item.edit;

import com.example.zoostore.api.operations.item.edit.tag.EditItemTagRequest;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagResponse;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagService;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.core.services.item.GetMultimediaService;
import com.example.zoostore.core.services.item.GetTagService;
import com.example.zoostore.core.services.item.GetVendorService;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EditItemTagServiceImpl implements EditItemTagService {
    private final ItemRepository itemRepository;
    private final GetTagService getTagService;
    private final GetVendorService getVendorService;
    private final GetMultimediaService getMultimediaService;

    @Override
    public EditItemTagResponse process(EditItemTagRequest editItemTagRequest) throws VendorNotFoundInRepositoryException, TagNotFoundInRepositoryException, MultimediaNotFoundInRepositoryException, ItemNotFoundInRepositoryException {
        Item itemFoundInRepository = findItemById(editItemTagRequest.getId());

        Set<Tag> tags = getTagService.getTagsByID(Collections.singleton(editItemTagRequest.getId()));

        itemFoundInRepository.setTags(tags);

        Item savedItem = itemRepository.save(itemFoundInRepository);

        return EditItemTagResponse.builder()
                .id(savedItem.getId())
                .title(savedItem.getTags().toString())
                .build();

        //TODO opravi returnovete da pravqt proverki dali kolekciqta e prazna
    }

    private Item findItemById(UUID id) throws ItemNotFoundInRepositoryException {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundInRepositoryException(HttpStatus.NOT_FOUND));
    }
}
