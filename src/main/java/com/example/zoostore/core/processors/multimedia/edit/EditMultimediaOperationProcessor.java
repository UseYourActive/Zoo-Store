package com.example.zoostore.core.processors.multimedia.edit;

import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLRequest;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLResponse;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditMultimediaOperationProcessor implements EditMultimediaOperation {
    private final MultimediaRepository multimediaRepository;

    @Override
    public EditMultimediaURLResponse process(EditMultimediaURLRequest editMultimediaURLRequest) {
        Multimedia foundInRepo = multimediaRepository.findById(editMultimediaURLRequest.getId())
                .orElseThrow(MultimediaNotFoundInRepositoryException::new);

        foundInRepo.setUrl(editMultimediaURLRequest.getUrl());

        Multimedia save = multimediaRepository.save(foundInRepo);

        return EditMultimediaURLResponse.builder()
                .id(save.getId())
                .url(save.getUrl())
                .build();
    }
}
