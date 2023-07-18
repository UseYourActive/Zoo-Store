package com.example.zoostore.core.exceptions.tag;

import org.springframework.http.HttpStatus;

public class TagNotFoundInRepositoryException extends TagException {
    public TagNotFoundInRepositoryException(HttpStatus s) {
    }

    public TagNotFoundInRepositoryException() {

    }
}
