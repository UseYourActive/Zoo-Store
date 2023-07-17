package com.example.zoostore.api.operations.multimedia.edit.url;

import com.example.zoostore.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.exceptions.multimedia.MultimediaNotFoundInRepositoryException;

public interface EditMultimediaService {
    EditMultimediaURLResponse editMultimediaURL(EditMultimediaURLRequest request) throws ItemNotFoundInRepositoryException, MultimediaNotFoundInRepositoryException;
}
