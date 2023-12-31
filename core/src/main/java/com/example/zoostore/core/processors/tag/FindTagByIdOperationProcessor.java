package com.example.zoostore.core.processors.tag;

import com.example.zoostore.api.operations.tag.find.byid.FindTagByIdOperation;
import com.example.zoostore.api.operations.tag.find.byid.FindTagByIdRequest;
import com.example.zoostore.api.operations.tag.find.byid.FindTagByIdResponse;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindTagByIdOperationProcessor implements FindTagByIdOperation {
    private final TagRepository tagRepository;

    @Override
    public FindTagByIdResponse process(final FindTagByIdRequest findTagByIdRequest) {
        log.info("Starting find tag by ID operation for tag ID: {}", findTagByIdRequest.getId());

        Tag tag = tagRepository.findById(UUID.fromString(findTagByIdRequest.getId()))
                .orElseThrow(TagNotFoundInRepositoryException::new);
        log.info("Found tag with ID: {}, Title: {}", tag.getId(), tag.getTitle());

        List<String> itemIds = tag.getItems().stream()
                .map(i -> String.valueOf(i.getId()))
                .toList();

        return FindTagByIdResponse.builder()
                .id(String.valueOf(tag.getId()))
                .title(tag.getTitle())
                .itemIds(itemIds)
                .build();
    }
}
