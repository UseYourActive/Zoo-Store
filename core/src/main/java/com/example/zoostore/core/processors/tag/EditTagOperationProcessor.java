package com.example.zoostore.core.processors.tag;

import com.example.zoostore.api.operations.tag.edit.title.EditTagTitleRequest;
import com.example.zoostore.api.operations.tag.edit.title.EditTagTitleResponse;
import com.example.zoostore.api.operations.tag.edit.title.EditTagTitleOperation;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class EditTagOperationProcessor implements EditTagTitleOperation {
    private final TagRepository tagRepository;

    @Override
    public EditTagTitleResponse process(final EditTagTitleRequest editTagNameRequest) {
        log.info("Starting edit tag operation for tag ID: {}", editTagNameRequest.getTagId());

        Tag tag = tagRepository.findTagById(UUID.fromString(editTagNameRequest.getTagId()))
                .orElseThrow(TagNotFoundInRepositoryException::new);

        tag.setTitle(editTagNameRequest.getTitle());
        tag.setItems(tag.getItems());

        Tag save = tagRepository.save(tag);
        log.info("Tag with ID {} successfully edited with new title: {}", save.getId(), save.getTitle());

        log.info("Edit tag operation completed");

        return EditTagTitleResponse.builder()
                .tagId(String.valueOf(save.getId()))
                .title(save.getTitle())
                .build();
    }
}
