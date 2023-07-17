package com.example.zoostore.api.operations.tag.edit.tag;

import com.example.zoostore.exceptions.tag.TagNotFoundInRepositoryException;

public interface EditTagService {
    EditTagNameResponse editTagName(EditTagNameRequest request) throws TagNotFoundInRepositoryException;
}
