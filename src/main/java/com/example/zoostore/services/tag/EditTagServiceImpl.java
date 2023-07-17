package com.example.zoostore.services.tag;

import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameRequest;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameResponse;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagService;
import com.example.zoostore.data.entities.Tag;
import com.example.zoostore.data.repositories.TagRepository;
import com.example.zoostore.exceptions.tag.TagNotFoundInRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EditTagServiceImpl implements EditTagService {
    private final TagRepository tagRepository;

    @Override
    public EditTagNameResponse editTagName(EditTagNameRequest request) throws TagNotFoundInRepositoryException {
        Tag foundInRepo = findItemById(request.getId());

        foundInRepo.setTitle(request.getTitle());

        Tag save = tagRepository.save(foundInRepo);

        return EditTagNameResponse.builder()
                .id(save.getId())
                .title(save.getTitle())
                .build();
    }

    private Tag findItemById(UUID id) throws TagNotFoundInRepositoryException {
        return tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundInRepositoryException(HttpStatus.NOT_FOUND));
    }
}
