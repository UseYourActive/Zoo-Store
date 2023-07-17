package com.example.zoostore.api.operations;

import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;

public interface OperationProcessor<Response extends OperationResult, Request extends OperationInput> {
    Response process(Request request) throws VendorNotFoundInRepositoryException, TagNotFoundInRepositoryException, MultimediaNotFoundInRepositoryException, ItemNotFoundInRepositoryException;
}
