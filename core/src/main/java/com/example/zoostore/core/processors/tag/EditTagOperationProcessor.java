package com.example.zoostore.core.processors.tag;

import com.example.zoostore.api.operations.tag.edit.title.EditTagTitleRequest;
import com.example.zoostore.api.operations.tag.edit.title.EditTagTitleResponse;
import com.example.zoostore.api.operations.tag.edit.title.EditTagTitleOperation;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditTagOperationProcessor implements EditTagTitleOperation {
    private final TagRepository tagRepository;

    @Override
    public EditTagTitleResponse process(EditTagTitleRequest editTagNameRequest) {
        Tag tag = tagRepository.findTagById(editTagNameRequest.getTagId())
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        tag.setTitle(editTagNameRequest.getTitle());
        tag.setItems(tag.getItems());

        Tag save = tagRepository.save(tag);

        return EditTagTitleResponse.builder()
                .tagId(save.getId())
                .title(save.getTitle())
                .build();
    }
}
