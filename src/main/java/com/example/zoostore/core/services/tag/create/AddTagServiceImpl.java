package com.example.zoostore.core.services.tag.create;

import com.example.zoostore.api.operations.tag.create.AddTagService;
import com.example.zoostore.api.operations.tag.create.CreateNewTagRequest;
import com.example.zoostore.api.operations.tag.create.CreateNewTagResponse;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.TagRepository;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddTagServiceImpl implements AddTagService {
    private final TagRepository tagRepository;

    @Override
    public CreateNewTagResponse process(CreateNewTagRequest createNewTagRequest) throws VendorNotFoundInRepositoryException, TagNotFoundInRepositoryException {
        Tag tag = Tag.builder()
                .title(createNewTagRequest.getTitle())
                .build();

        Tag save = tagRepository.save(tag);

        return CreateNewTagResponse.builder()
                .id(save.getId())
                .title(save.getTitle())
                .build();
    }
}
