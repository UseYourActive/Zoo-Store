package com.example.zoostore.exceptions.tag;

import org.springframework.http.HttpStatus;

public class TagNotFoundInRepositoryException extends TagException {
    public TagNotFoundInRepositoryException(HttpStatus s) {
    }
}
