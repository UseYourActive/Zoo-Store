package com.example.zoostore.core.processors.multimedia;

import com.example.zoostore.api.operations.multimedia.find.all.FindAllMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.find.all.FindAllMultimediaRepo;
import com.example.zoostore.api.operations.multimedia.find.all.FindAllMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.find.all.FindAllMultimediaResponse;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindAllMultimediaOperationProcessor implements FindAllMultimediaOperation {
    private final MultimediaRepository multimediaRepository;

    @Override
    public FindAllMultimediaResponse process(FindAllMultimediaRequest findAllMultimediaRequest) {
        log.info("Starting find all multimedia operation");

        List<Multimedia> itemsFoundInRepo = multimediaRepository.findAll();
        log.info("Found {} multimedia items in the repository", itemsFoundInRepo.size());

        List<FindAllMultimediaRepo> multimediaList = itemsFoundInRepo.stream()
                .map(this::mapAllMultimediaInRepo)
                .toList();
        log.info("Find all multimedia operation completed");

        return FindAllMultimediaResponse.builder()
                .multimedia(multimediaList)
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
