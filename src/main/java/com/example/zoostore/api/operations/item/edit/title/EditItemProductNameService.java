package com.example.zoostore.api.operations.item.edit.title;

import com.example.zoostore.exceptions.item.ItemNotFoundInRepositoryException;

public interface EditItemProductNameService {
    EditItemTitleResponse editItemProductName(EditItemTitleRequest request) throws ItemNotFoundInRepositoryException;
}
