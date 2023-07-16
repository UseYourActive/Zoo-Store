package com.example.zoostore.services.tag;

import com.example.zoostore.api.operations.tag.create.AddTagService;
import com.example.zoostore.api.operations.tag.create.CreateNewTagRequest;
import com.example.zoostore.api.operations.tag.create.CreateNewTagResponse;
import com.example.zoostore.data.entities.Tag;
import com.example.zoostore.data.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddTagServiceImpl implements AddTagService {
    private final TagRepository tagRepository;

    @Override
    public CreateNewTagResponse addTag(CreateNewTagRequest request) {
        Tag tag = Tag.builder()
                .title(request.getTitle())
                .build();

        Tag save = tagRepository.save(tag);

        return CreateNewTagResponse.builder()
                .id(save.getId())
                .title(save.getTitle())
                .build();
    }
}
