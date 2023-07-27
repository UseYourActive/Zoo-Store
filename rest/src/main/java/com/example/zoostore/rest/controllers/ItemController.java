package com.example.zoostore.rest.controllers;

import com.example.zoostore.api.operations.item.findall.FindAllItemsInput;
import com.example.zoostore.api.operations.item.findall.FindAllItemsOperation;
import com.example.zoostore.api.operations.item.findall.FindAllItemsResult;
import com.example.zoostore.api.operations.item.findbyid.FindItemByIdRequest;
import com.example.zoostore.api.operations.item.findbyid.FindItemByIdResponse;
import com.example.zoostore.api.operations.item.archive.ArchiveItemRequest;
import com.example.zoostore.api.operations.item.archive.ArchiveItemResponse;
import com.example.zoostore.api.operations.item.archive.ArchiveItemOperation;
import com.example.zoostore.api.operations.item.create.CreateNewItemRequest;
import com.example.zoostore.api.operations.item.create.CreateNewItemResponse;
import com.example.zoostore.api.operations.item.create.CreateNewItemOperation;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionRequest;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionResponse;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionOperation;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLRequest;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLResponse;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLOperation;
import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameOperation;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagRequest;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagResponse;
import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameRequest;
import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameResponse;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagOperation;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorRequest;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorResponse;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorOperation;
import com.example.zoostore.api.operations.item.findbyid.FindItemByIdOperation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/items")
public class ItemController {
    private final CreateNewItemOperation createNewItemOperation;
    private final EditItemVendorOperation editItemVendorOperation;
    private final EditItemProductNameOperation editItemProductNameOperation;
    private final EditItemDescriptionOperation editItemDescriptionOperation;
    private final EditItemMultimediaURLOperation editItemMultimediaURLOperation;
    private final EditItemTagOperation editItemTagOperation;
    private final ArchiveItemOperation archiveItemOperation;
    private final FindItemByIdOperation findItemByIdOperation;
    private final FindAllItemsOperation findAllItemsOperation;

    @Operation(description = "From the users request creates a new item that does not exist in the database yet.",
            summary = "Creates a new item.")
    @PostMapping("/create")
    public ResponseEntity<CreateNewItemResponse> createItem(@Valid  @RequestBody CreateNewItemRequest request) {
        return new ResponseEntity<>(createNewItemOperation.process(request), HttpStatus.CREATED);
    }

    @Operation(description = "Edits an existing in the database name of the product with the given id from the users request.",
            summary = "Edits a products name.")
    @PatchMapping("/edit/product-name")
    public ResponseEntity<EditItemProductNameResponse> editItemProductName(@Valid @RequestBody EditItemProductNameRequest request) {
        return new ResponseEntity<>(editItemProductNameOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database description of the product with the given id from the users request.",
            summary = "Edits a products description.")
    @PatchMapping("/edit/description")
    public ResponseEntity<EditItemDescriptionResponse> editItemDescription(@Valid @RequestBody EditItemDescriptionRequest request) {
        return new ResponseEntity<>(editItemDescriptionOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database vendor of the product with the given id from the users request.",
            summary = "Edits a products vendor.")
    @PatchMapping("/edit/vendor")
    public ResponseEntity<EditItemVendorResponse> editItemVendor(@Valid @RequestBody EditItemVendorRequest request) {
        return new ResponseEntity<>(editItemVendorOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database urls of the product with the given id from the users request.",
            summary = "Edits a products urls.")
    @PatchMapping("/edit/multimedia")
    public ResponseEntity<EditItemMultimediaURLResponse> replaceItemMultimediaURL(@Valid @RequestBody EditItemMultimediaURLRequest request) {
        return new ResponseEntity<>(editItemMultimediaURLOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database tag title of the product with the given id from the users request.",
            summary = "Edits a products tag title.")
    @PatchMapping("/edit/tag")
    public ResponseEntity<EditItemTagResponse> editItemTag(@Valid @RequestBody EditItemTagRequest request) {
        return new ResponseEntity<>(editItemTagOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Archives an existing in the database product with the given id from the users request, hiding it from the clients vision.",
            summary = "Archives an item.")
    @PatchMapping("/archive")
    public ResponseEntity<ArchiveItemResponse> archiveItem(@Valid @RequestBody ArchiveItemRequest request) {
        return new ResponseEntity<>(archiveItemOperation.process(request), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{request}")
    public ResponseEntity<FindItemByIdResponse> getItemById(@PathVariable @org.hibernate.validator.constraints.UUID String request){
        FindItemByIdRequest build = FindItemByIdRequest.builder()
                .id(UUID.fromString(request))
                .build();

        return new ResponseEntity<>(findItemByIdOperation.process(build), HttpStatus.OK);
    }

    //(required = false, defaultValue = "false")
    @GetMapping("/find-all")
    public ResponseEntity<FindAllItemsResult> getAllItems(@RequestParam Boolean includeArchived,
                                                          @RequestParam Integer pageNumber,
                                                          @RequestParam Integer numberOfItemsPerPage,
                                                          @RequestParam @org.hibernate.validator.constraints.UUID String tagId) {
        FindAllItemsInput build = FindAllItemsInput.builder()
                .shouldIncludeArchivedItems(includeArchived)
                .numberOfItemsPerPage(numberOfItemsPerPage)
                .pageNumber(pageNumber)
                .tagId(UUID.fromString(tagId))
                .build();

        return new ResponseEntity<>(findAllItemsOperation.process(build), HttpStatus.OK);
    }
}
