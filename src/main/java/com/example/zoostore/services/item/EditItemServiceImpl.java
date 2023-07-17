package com.example.zoostore.services.item;

import com.example.zoostore.api.operations.item.edit.EditItemService;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionRequest;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionResponse;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLRequest;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLResponse;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagRequest;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagResponse;
import com.example.zoostore.api.operations.item.edit.title.EditItemProductNameRequest;
import com.example.zoostore.api.operations.item.edit.title.EditItemProductNameResponse;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorRequest;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorResponse;
import com.example.zoostore.data.entities.Item;
import com.example.zoostore.data.entities.Multimedia;
import com.example.zoostore.data.entities.Tag;
import com.example.zoostore.data.entities.Vendor;
import com.example.zoostore.data.repositories.ItemRepository;
import com.example.zoostore.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.exceptions.vendor.VendorNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EditItemServiceImpl implements EditItemService {
    private final ItemRepository itemRepository;
    private final GetTagService getTagService;
    private final GetVendorService getVendorService;
    private final GetMultimediaService getMultimediaService;

    @Override
    public EditItemProductNameResponse editItemProductName(EditItemProductNameRequest request) throws ItemNotFoundInRepositoryException {
        Item itemFoundInRepository = findItemById(request.getId());

        itemFoundInRepository.setProductName(request.getTitle());

        Item savedItem = itemRepository.save(itemFoundInRepository);

        return EditItemProductNameResponse.builder()
                .id(savedItem.getId())
                .title(savedItem.getProductName())
                .build();
    }

    @Override
    public EditItemDescriptionResponse editItemDescription(EditItemDescriptionRequest request) throws ItemNotFoundInRepositoryException {
        Item itemFoundInRepository = findItemById(request.getId());

        itemFoundInRepository.setDescription(request.getDescription());

        Item savedItem = itemRepository.save(itemFoundInRepository);

        return EditItemDescriptionResponse.builder()
                .id(savedItem.getId())
                .description(savedItem.getDescription())
                .build();
    }

    @Override
    public EditItemVendorResponse editItemVendor(EditItemVendorRequest request) throws ItemNotFoundInRepositoryException, VendorNotFoundInRepositoryException {
        Item itemFoundInRepository = findItemById(request.getId());

        Optional<Vendor> vendorOptional = Optional.ofNullable(getVendorService.getVendorsByID(request.getVendor()));

        Vendor vendor = vendorOptional
                .orElseThrow(() -> new VendorNotFoundInRepositoryException(HttpStatus.NOT_FOUND));

        itemFoundInRepository.setVendor(vendor);

        Item savedItem = itemRepository.save(itemFoundInRepository);

        return EditItemVendorResponse.builder()
                .id(savedItem.getId())
                .vendor(savedItem.getVendor().getId())
                .build();
    }

    @Override
    public EditItemMultimediaURLResponse replaceItemMultimediaURL(EditItemMultimediaURLRequest request) throws ItemNotFoundInRepositoryException {
        Item itemFoundInRepository = findItemById(request.getId());

        Set<Multimedia> multimediaSet = request.getUrl().stream()
                .map(url -> Multimedia.builder().url(url).build())
                .collect(Collectors.toSet());

        itemFoundInRepository.setMultimedia(multimediaSet);

        Item savedItem = itemRepository.save(itemFoundInRepository);

        Set<String> multimediaUrls = savedItem.getMultimedia().stream()
                .map(Multimedia::getUrl)
                .collect(Collectors.toSet());

        return EditItemMultimediaURLResponse.builder()
                .id(savedItem.getId())
                .multimedia(multimediaUrls)
                .build();

        //TODO opravi returnovete da pravqt proverki dali kolekciqta e prazna
    }

    @Override
    public EditItemTagResponse editItemTag(EditItemTagRequest request) throws ItemNotFoundInRepositoryException {
        Item itemFoundInRepository = findItemById(request.getId());

        Set<Tag> tags = getTagService.getTagsByID(Collections.singleton(request.getId()));

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
