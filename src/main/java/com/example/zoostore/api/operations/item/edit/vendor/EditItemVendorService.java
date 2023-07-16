package com.example.zoostore.api.operations.item.edit.vendor;

import com.example.zoostore.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.exceptions.vendor.VendorNotFoundInRepositoryException;

public interface EditItemVendorService {

    EditItemVendorResponse editItemVendor(EditItemVendorRequest request) throws ItemNotFoundInRepositoryException, VendorNotFoundInRepositoryException;
}
