package com.example.zoostore.exceptions.item;

import org.springframework.http.HttpStatus;

public class ItemNotFoundInRepositoryException extends ItemException {
    public ItemNotFoundInRepositoryException(HttpStatus status) {
    }
}
