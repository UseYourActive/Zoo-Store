package com.example.zoostore.core.processors.multimedia;

import com.example.zoostore.api.operations.multimedia.findall.FindAllMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.findall.FindAllMultimediaRepo;
import com.example.zoostore.api.operations.multimedia.findall.FindAllMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.findall.FindAllMultimediaResponse;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindAllMultimediaOperationProcessor implements FindAllMultimediaOperation {
    private final MultimediaRepository multimediaRepository;

    @Override
    public FindAllMultimediaResponse process(FindAllMultimediaRequest findAllMultimediaRequest) {
        List<Multimedia> itemsFoundInRepo = multimediaRepository.findAll();

        return FindAllMultimediaResponse.builder()
                .multimedia(itemsFoundInRepo.stream()
                        .map(this::mapAllMultimediaInRepo)
                        .collect(Collectors.toSet()))
                .build();
    }

    private FindAllMultimediaRepo mapAllMultimediaInRepo(Multimedia multimedia){
        return FindAllMultimediaRepo.builder()
                .id(multimedia.getId())
                .itemId(multimedia.getItem().getId())
                .url(multimedia.getUrl())
                .build();
    }
}