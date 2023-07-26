package com.example.zoostore.core.processors.multimedia.create;

import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaResponse;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateNewMultimediaOperationProcessor implements CreateNewMultimediaOperation {
    private final ItemRepository itemRepository;
    private final MultimediaRepository multimediaRepository;

    @Override
    public CreateNewMultimediaResponse process(CreateNewMultimediaRequest createNewMultimediaRequest) {
        Item item = itemRepository.findById(createNewMultimediaRequest.getItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        Multimedia multimedia = Multimedia.builder()
                .item(item)
                .url(createNewMultimediaRequest.getUrl())
                .build();

        Multimedia save = multimediaRepository.save(multimedia);

        return CreateNewMultimediaResponse.builder()
                .itemId(save.getId())
                .url(save.getUrl())
                .build();
    }
}
