package com.example.zoostore.core.services.multimedia.edit;

import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaService;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLRequest;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLResponse;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EditMultimediaServiceImpl implements EditMultimediaService {
    private final MultimediaRepository multimediaRepository;

    @Override
    public EditMultimediaURLResponse process(EditMultimediaURLRequest editMultimediaURLRequest) throws MultimediaNotFoundInRepositoryException {
        Multimedia foundInRepo = findItemById(editMultimediaURLRequest.getId());

        foundInRepo.setUrl(editMultimediaURLRequest.getUrl());

        Multimedia save = multimediaRepository.save(foundInRepo);

        return EditMultimediaURLResponse.builder()
                .id(save.getId())
                .url(save.getUrl())
                .build();
    }

    private Multimedia findItemById(UUID id) throws MultimediaNotFoundInRepositoryException {
        return multimediaRepository.findById(id)
                .orElseThrow(() -> new MultimediaNotFoundInRepositoryException(HttpStatus.NOT_FOUND));
    }
}
