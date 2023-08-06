package com.example.zoostore.core.processors.multimedia;

import com.example.zoostore.api.operations.multimedia.edit.item.EditMultimediaItemOperation;
import com.example.zoostore.api.operations.multimedia.edit.item.EditMultimediaItemRequest;
import com.example.zoostore.api.operations.multimedia.edit.item.EditMultimediaItemResponse;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditMultimediaItemOperationProcessor implements EditMultimediaItemOperation {
    private final MultimediaRepository multimediaRepository;
    private final ItemRepository itemRepository;

    @Override
    public EditMultimediaItemResponse process(EditMultimediaItemRequest editMultimediaItemRequest) {
        Multimedia multimedia = multimediaRepository.findById(editMultimediaItemRequest.getMultimediaId())
                .orElseThrow(MultimediaNotFoundInRepositoryException::new);

        Item item = itemRepository.findById(editMultimediaItemRequest.getItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        multimedia.setItem(item);

        Multimedia save = multimediaRepository.save(multimedia);

        return EditMultimediaItemResponse.builder()
                .id(save.getId())
                .itemId(save.getItem().getId())
                .url(save.getUrl())
                .build();
    }
}
