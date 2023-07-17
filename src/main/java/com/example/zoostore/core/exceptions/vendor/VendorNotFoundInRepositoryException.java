package com.example.zoostore.core.exceptions.vendor;

import org.springframework.http.HttpStatus;

public class VendorNotFoundInRepositoryException extends VendorException {
    public VendorNotFoundInRepositoryException(HttpStatus httpStatus) {
    }
}
