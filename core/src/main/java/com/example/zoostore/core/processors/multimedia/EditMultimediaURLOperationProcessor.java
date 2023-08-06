package com.example.zoostore.core.processors.multimedia;

import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLOperation;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLRequest;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLResponse;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditMultimediaURLOperationProcessor implements EditMultimediaURLOperation {
    private final MultimediaRepository multimediaRepository;

    @Override
    public EditMultimediaURLResponse process(EditMultimediaURLRequest editMultimediaURLRequest) {
        Multimedia multimedia = multimediaRepository.findMultimediaById(editMultimediaURLRequest.getMultimediaId())
                .orElseThrow(MultimediaNotFoundInRepositoryException::new);

        multimedia.setUrl(editMultimediaURLRequest.getUrl());

        Multimedia save = multimediaRepository.save(multimedia);

        return EditMultimediaURLResponse.builder()
                .id(save.getId())
                .itemId(save.getId())
                .url(save.getUrl())
                .build();
    }
}
