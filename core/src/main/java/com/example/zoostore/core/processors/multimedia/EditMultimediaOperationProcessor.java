package com.example.zoostore.core.processors.multimedia;

import com.example.zoostore.api.operations.multimedia.edit.full.EditMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.edit.full.EditMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.edit.full.EditMultimediaResponse;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EditMultimediaOperationProcessor implements EditMultimediaOperation {
    private final MultimediaRepository multimediaRepository;
    private final ItemRepository itemRepository;

    @Override
    public EditMultimediaResponse process(EditMultimediaRequest editMultimediaRequest) {
        Multimedia multimedia = multimediaRepository.findById(editMultimediaRequest.getMultimediaId())
                .orElseThrow(MultimediaNotFoundInRepositoryException::new);

        Optional.ofNullable(editMultimediaRequest.getItemId())
                .ifPresent(itemId -> {
                    Item item = itemRepository.findById(editMultimediaRequest.getItemId())
                            .orElseThrow(ItemNotFoundInRepositoryException::new);
                    multimedia.setItem(item);
                });

        Optional.ofNullable(editMultimediaRequest.getUrl())
                .ifPresent(multimedia::setUrl);

        Multimedia save = this.multimediaRepository.save(multimedia);

        return EditMultimediaResponse.builder()
                .id(save.getId())
                .url(save.getUrl())
                .itemId(save.getItem().getId())
                .build();
    }
}
