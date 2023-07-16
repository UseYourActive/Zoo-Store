package com.example.zoostore.api.operations.item.edit.multimedia;

import com.example.zoostore.exceptions.item.ItemNotFoundInRepositoryException;

public interface EditItemMultimediaURLService {
    EditItemMultimediaURLResponse replaceItemMultimediaURL(EditItemMultimediaURLRequest request) throws ItemNotFoundInRepositoryException;
}
