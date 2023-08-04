package com.example.zoostore.core.processors.multimedia;

import com.example.zoostore.api.operations.multimedia.findbyid.FindMultimediaByIdOperation;
import com.example.zoostore.api.operations.multimedia.findbyid.FindMultimediaByIdRequest;
import com.example.zoostore.api.operations.multimedia.findbyid.FindMultimediaByIdResponse;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindMultimediaByIdOperationProcessor implements FindMultimediaByIdOperation {
    private final MultimediaRepository multimediaRepository;

    @Override
    public FindMultimediaByIdResponse process(FindMultimediaByIdRequest findMultimediaByIdRequest) {
        Multimedia multimedia = multimediaRepository.findById(findMultimediaByIdRequest.getId())
                .orElseThrow(MultimediaNotFoundInRepositoryException::new);

        return FindMultimediaByIdResponse.builder()
                .id(multimedia.getId())
                .url(multimedia.getUrl())
                .itemId(multimedia.getItem().getId())
                .build();
    }
}
