package com.example.zoostore.exceptions.vendor;

import org.springframework.http.HttpStatus;

public class VendorNotFoundInRepositoryException extends VendorException {
    public VendorNotFoundInRepositoryException(HttpStatus httpStatus) {
    }
}
