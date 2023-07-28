package com.example.zoostore.core.processors.tag.findbyid;

import com.example.zoostore.api.operations.tag.findbyid.FindTagByIdOperation;
import com.example.zoostore.api.operations.tag.findbyid.FindTagByIdRequest;
import com.example.zoostore.api.operations.tag.findbyid.FindTagByIdResponse;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindTagByIdOperationProcessor implements FindTagByIdOperation {
    private final TagRepository tagRepository;

    @Override
    public FindTagByIdResponse process(FindTagByIdRequest findTagByIdRequest) {
        Tag tag = tagRepository.findById(findTagByIdRequest.getId())
                .orElseThrow(TagNotFoundInRepositoryException::new);

        return FindTagByIdResponse.builder()
                .id(tag.getId())
                .title(tag.getTitle())
                .itemIds(tag.getItems().stream()
                        .map(Item::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
