package com.example.zoostore.api.operations.tag.edit;

import com.example.zoostore.exceptions.tag.TagNotFoundInRepositoryException;

public interface EditTagService {
    EditTagNameResponse editTagName(EditTagNameRequest request) throws TagNotFoundInRepositoryException;
}
