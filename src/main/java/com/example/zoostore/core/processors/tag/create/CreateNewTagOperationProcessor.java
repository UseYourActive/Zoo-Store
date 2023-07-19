package com.example.zoostore.core.processors.tag.create;

import com.example.zoostore.api.operations.tag.create.CreateNewTagOperation;
import com.example.zoostore.api.operations.tag.create.CreateNewTagRequest;
import com.example.zoostore.api.operations.tag.create.CreateNewTagResponse;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateNewTagOperationProcessor implements CreateNewTagOperation {
    private final TagRepository tagRepository;

    @Override
    public CreateNewTagResponse process(CreateNewTagRequest createNewTagRequest) {
        Tag tag = Tag.builder()
                .title(createNewTagRequest.getTitle())
                .build();

        Tag save = tagRepository.save(tag);

        return CreateNewTagResponse.builder()
                .tagId(save.getId())
                .title(save.getTitle())
                .build();
    }
}
