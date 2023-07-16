package com.example.zoostore.api.operations.multimedia.create;

import com.example.zoostore.exceptions.item.ItemNotFoundInRepositoryException;

public interface AddMultimediaService {
    CreateNewMultimediaResponse addMultimedia(CreateNewMultimediaRequest request) throws ItemNotFoundInRepositoryException;
}
