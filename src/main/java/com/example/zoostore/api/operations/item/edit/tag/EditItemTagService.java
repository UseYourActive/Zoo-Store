package com.example.zoostore.api.operations.item.edit.tag;

import com.example.zoostore.exceptions.item.ItemNotFoundInRepositoryException;

public interface EditItemTagService {
    EditItemTagResponse editItemTag(EditItemTagRequest request) throws ItemNotFoundInRepositoryException;
}
