package com.example.zoostore.core.processors.tag.edit;

import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameRequest;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameResponse;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagOperation;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.TagRepository;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EditTagOperationProcessor implements EditTagOperation {
    private final TagRepository tagRepository;

    @Override
    public EditTagNameResponse process(EditTagNameRequest editTagNameRequest) {
        Tag foundInRepo = tagRepository.findById(editTagNameRequest.getTagId())
                .orElseThrow(TagNotFoundInRepositoryException::new);

        foundInRepo.setTitle(editTagNameRequest.getTitle());

        Tag save = tagRepository.save(foundInRepo);

        return EditTagNameResponse.builder()
                .tagId(save.getId())
                .title(save.getTitle())
                .build();
    }
}
