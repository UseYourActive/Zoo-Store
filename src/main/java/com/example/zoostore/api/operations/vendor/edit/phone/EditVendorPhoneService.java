package com.example.zoostore.api.operations.vendor.edit.phone;

import com.example.zoostore.exceptions.vendor.VendorNotFoundInRepositoryException;

public interface EditVendorPhoneService {
    EditVendorPhoneResponse editVendorPhone(EditVendorPhoneRequest request) throws VendorNotFoundInRepositoryException;
}
