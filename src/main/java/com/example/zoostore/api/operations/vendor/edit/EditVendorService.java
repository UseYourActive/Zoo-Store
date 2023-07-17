package com.example.zoostore.api.operations.vendor.edit;

import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameRequest;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameResponse;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneRequest;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneResponse;
import com.example.zoostore.exceptions.vendor.VendorNotFoundInRepositoryException;

public interface EditVendorService {
    EditVendorPhoneResponse editVendorPhone(EditVendorPhoneRequest request) throws VendorNotFoundInRepositoryException;
    EditVendorNameResponse editVendorName(EditVendorNameRequest request) throws VendorNotFoundInRepositoryException;
}
