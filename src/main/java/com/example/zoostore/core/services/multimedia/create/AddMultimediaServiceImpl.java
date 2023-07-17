package com.example.zoostore.core.services.multimedia.create;

import com.example.zoostore.api.operations.multimedia.create.AddMultimediaService;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaResponse;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AddMultimediaServiceImpl implements AddMultimediaService {
    private final ItemRepository itemRepository;
    private final MultimediaRepository multimediaRepository;

    @Override
    public CreateNewMultimediaResponse process(CreateNewMultimediaRequest createNewMultimediaRequest) throws ItemNotFoundInRepositoryException {
        Item item = findItemById(createNewMultimediaRequest.getId());

        Multimedia multimedia = Multimedia.builder()
                .item(item)
                .url(createNewMultimediaRequest.getUrl())
                .build();

        Multimedia save = multimediaRepository.save(multimedia);

        return CreateNewMultimediaResponse.builder()
                .id(save.getId())
                .url(save.getUrl())
                .build();
    }

    private Item findItemById(UUID id) throws ItemNotFoundInRepositoryException {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundInRepositoryException(HttpStatus.NOT_FOUND));
    }
}
