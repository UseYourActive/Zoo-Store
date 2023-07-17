package com.example.zoostore.rest.controllers;

import com.example.zoostore.api.operations.item.archive.ArchiveItemRequest;
import com.example.zoostore.api.operations.item.archive.ArchiveItemResponse;
import com.example.zoostore.api.operations.item.archive.ArchiveItemService;
import com.example.zoostore.api.operations.item.create.CreateNewItemRequest;
import com.example.zoostore.api.operations.item.create.CreateNewItemResponse;
import com.example.zoostore.api.operations.item.create.CreateItemService;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionRequest;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionResponse;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionService;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLRequest;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLResponse;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLService;
import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameService;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagRequest;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagResponse;
import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameRequest;
import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameResponse;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagService;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorRequest;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorResponse;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorService;
import com.example.zoostore.core.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.core.exceptions.vendor.VendorNotFoundInRepositoryException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {
    private final CreateItemService createItemService;
    private final EditItemVendorService editItemVendorService;
    private final EditItemProductNameService editItemProductNameService;
    private final EditItemDescriptionService editItemDescriptionService;
    private final EditItemMultimediaURLService editItemMultimediaURLService;
    private final EditItemTagService editItemTagService;
    private final ArchiveItemService archiveItemService;

    @Operation(description = "From the users request creates a new item that does not exist in the database yet.", summary = "Creates a new item.")
    @PostMapping("/create")
    public ResponseEntity<CreateNewItemResponse> createItem(@RequestBody CreateNewItemRequest request) throws MultimediaNotFoundInRepositoryException, ItemNotFoundInRepositoryException, TagNotFoundInRepositoryException, VendorNotFoundInRepositoryException {
        return new ResponseEntity<>(createItemService.process(request), HttpStatus.CREATED);
    }

    @Operation(description = "Edits an existing in the database name of the product with the given id from the users request.", summary = "Edits a products name.")
    @PatchMapping("/edit/product-name")
    public ResponseEntity<EditItemProductNameResponse> editItemProductName(@RequestBody EditItemProductNameRequest request) throws ItemNotFoundInRepositoryException, MultimediaNotFoundInRepositoryException, TagNotFoundInRepositoryException, VendorNotFoundInRepositoryException {
        return new ResponseEntity<>(editItemProductNameService.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database description of the product with the given id from the users request.", summary = "Edits a products description.")
    @PatchMapping("/edit/description")
    public ResponseEntity<EditItemDescriptionResponse> editItemDescription(@RequestBody EditItemDescriptionRequest request) throws ItemNotFoundInRepositoryException, MultimediaNotFoundInRepositoryException, TagNotFoundInRepositoryException, VendorNotFoundInRepositoryException {
        return new ResponseEntity<>(editItemDescriptionService.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database vendor of the product with the given id from the users request.", summary = "Edits a products vendor.")
    @PatchMapping("/edit/vendor")
    public ResponseEntity<EditItemVendorResponse> editItemVendor(@RequestBody EditItemVendorRequest request) throws ItemNotFoundInRepositoryException, VendorNotFoundInRepositoryException, MultimediaNotFoundInRepositoryException, TagNotFoundInRepositoryException {
        return new ResponseEntity<>(editItemVendorService.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database urls of the product with the given id from the users request.", summary = "Edits a products urls.")
    @PatchMapping("/edit/multimedia")
    public ResponseEntity<EditItemMultimediaURLResponse> replaceItemMultimediaURL(@RequestBody EditItemMultimediaURLRequest request) throws ItemNotFoundInRepositoryException, MultimediaNotFoundInRepositoryException, TagNotFoundInRepositoryException, VendorNotFoundInRepositoryException {
        return new ResponseEntity<>(editItemMultimediaURLService.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database tag title of the product with the given id from the users request.", summary = "Edits a products tag title.")
    @PatchMapping("/edit/tag")
    public ResponseEntity<EditItemTagResponse> editItemTag(@RequestBody EditItemTagRequest request) throws ItemNotFoundInRepositoryException, MultimediaNotFoundInRepositoryException, TagNotFoundInRepositoryException, VendorNotFoundInRepositoryException {
        return new ResponseEntity<>(editItemTagService.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Archives an existing in the database product with the given id from the users request, hiding it from the clients vision.", summary = "Archives an item.")
    @PatchMapping("/archive")
    public ResponseEntity<ArchiveItemResponse> archiveItem(@RequestBody ArchiveItemRequest request) throws ItemNotFoundInRepositoryException, MultimediaNotFoundInRepositoryException, TagNotFoundInRepositoryException, VendorNotFoundInRepositoryException {
        return new ResponseEntity<>(archiveItemService.process(request), HttpStatus.ACCEPTED);
    }
}
