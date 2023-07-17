package com.example.zoostore.core.processors.item.edit;

import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLRequest;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLResponse;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLOperation;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.processors.item.GetMultimediaService;
import com.example.zoostore.core.processors.item.GetTagService;
import com.example.zoostore.core.processors.item.GetVendorService;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EditItemMultimediaURLOperationProcessor implements EditItemMultimediaURLOperation {
    private final ItemRepository itemRepository;
    private final GetTagService getTagService;
    private final GetVendorService getVendorService;
    private final GetMultimediaService getMultimediaService;

    @Override
    public EditItemMultimediaURLResponse process(EditItemMultimediaURLRequest editItemMultimediaURLRequest) {
        Item itemFoundInRepository = findItemById(editItemMultimediaURLRequest.getId());

        Set<Multimedia> multimediaSet = editItemMultimediaURLRequest.getUrl().stream()
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

    private Item findItemById(UUID id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundInRepositoryException(HttpStatus.NOT_FOUND));
    }
}
