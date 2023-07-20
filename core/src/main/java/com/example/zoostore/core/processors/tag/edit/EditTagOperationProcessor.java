package com.example.zoostore.core.processors.tag.edit;

import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameRequest;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameResponse;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagOperation;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.TagRepository;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EditTagOperationProcessor implements EditTagOperation {
    private final TagRepository tagRepository;

    @Override
    public EditTagNameResponse process(EditTagNameRequest editTagNameRequest) {
        Optional<Tag> tagOptional = tagRepository.findTagById(editTagNameRequest.getTagId());

        Tag tag = tagOptional
                .orElseThrow(VendorNotFoundInRepositoryException::new);

        tag.setTitle(editTagNameRequest.getTitle());
        tag.setItems(tag.getItems());

        Tag save = tagRepository.save(tag);

        return EditTagNameResponse.builder()
                .tagId(save.getId())
                .title(save.getTitle())
                .build();
    }
}
