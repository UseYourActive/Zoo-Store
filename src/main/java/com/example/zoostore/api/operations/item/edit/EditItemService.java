package com.example.zoostore.api.operations.item.edit;

import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionRequest;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionResponse;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLRequest;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLResponse;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagRequest;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagResponse;
import com.example.zoostore.api.operations.item.edit.title.EditItemProductNameRequest;
import com.example.zoostore.api.operations.item.edit.title.EditItemProductNameResponse;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorRequest;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorResponse;
import com.example.zoostore.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.exceptions.vendor.VendorNotFoundInRepositoryException;

public interface EditItemService {
    EditItemVendorResponse editItemVendor(EditItemVendorRequest request) throws ItemNotFoundInRepositoryException, VendorNotFoundInRepositoryException;
    EditItemDescriptionResponse editItemDescription(EditItemDescriptionRequest request) throws ItemNotFoundInRepositoryException;
    EditItemMultimediaURLResponse replaceItemMultimediaURL(EditItemMultimediaURLRequest request) throws ItemNotFoundInRepositoryException;
    EditItemTagResponse editItemTag(EditItemTagRequest request) throws ItemNotFoundInRepositoryException;
    EditItemProductNameResponse editItemProductName(EditItemProductNameRequest request) throws ItemNotFoundInRepositoryException;

}
