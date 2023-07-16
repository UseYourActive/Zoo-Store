package com.example.zoostore.api.operations.item.edit.description;

import com.example.zoostore.exceptions.item.ItemNotFoundInRepositoryException;

public interface EditItemDescriptionService {
    EditItemDescriptionResponse editItemDescription(EditItemDescriptionRequest request) throws ItemNotFoundInRepositoryException;
}
