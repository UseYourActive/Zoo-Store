package com.example.zoostore.services.multimedia;

import com.example.zoostore.api.operations.multimedia.create.AddMultimediaService;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaResponse;
import com.example.zoostore.data.entities.Item;
import com.example.zoostore.data.entities.Multimedia;
import com.example.zoostore.data.repositories.ItemRepository;
import com.example.zoostore.data.repositories.MultimediaRepository;
import com.example.zoostore.exceptions.item.ItemNotFoundInRepositoryException;
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
    public CreateNewMultimediaResponse addMultimedia(CreateNewMultimediaRequest request) throws ItemNotFoundInRepositoryException {
        Item item = findItemById(request.getId());

        Multimedia multimedia = Multimedia.builder()
                .item(item)
                .url(request.getUrl())
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
