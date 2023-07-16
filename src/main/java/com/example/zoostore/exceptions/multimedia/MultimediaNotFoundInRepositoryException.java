package com.example.zoostore.exceptions.multimedia;

import org.springframework.http.HttpStatus;

public class MultimediaNotFoundInRepositoryException extends MultimediaException {
    public MultimediaNotFoundInRepositoryException(HttpStatus s) {
    }
}
