package com.example.zoostore.api.operations.vendor.edit.name;

import com.example.zoostore.exceptions.vendor.VendorNotFoundInRepositoryException;

public interface EditVendorNameService {
    EditVendorNameResponse editVendorName(EditVendorNameRequest request) throws VendorNotFoundInRepositoryException;
}
