package com.example.zoostore.core.processors.tag;

import com.example.zoostore.api.operations.tag.create.CreateNewTagOperation;
import com.example.zoostore.api.operations.tag.create.CreateNewTagRequest;
import com.example.zoostore.api.operations.tag.create.CreateNewTagResponse;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CreateNewTagOperationProcessor implements CreateNewTagOperation {
    private final TagRepository tagRepository;

    @Override
    public CreateNewTagResponse process(CreateNewTagRequest createNewTagRequest) {
        log.info("Starting create new tag operation with title: {}", createNewTagRequest.getTitle());

        Tag tag = Tag.builder()
                .title(createNewTagRequest.getTitle())
                .items(new HashSet<>())
                .build();

        Tag save = tagRepository.save(tag);
        log.info("Tag created with ID: {}", save.getId());

        CreateNewTagResponse response = CreateNewTagResponse.builder()
                .tagId(save.getId())
                .title(save.getTitle())
                .itemIds(save.getItems().stream()
                        .map(Item::getId)
                        .toList())
                .build();
        log.info("Create new tag operation completed");

        return response;
    }
}
