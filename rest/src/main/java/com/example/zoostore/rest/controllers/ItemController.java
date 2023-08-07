package com.example.zoostore.rest.controllers;

import com.example.zoostore.api.operations.item.edit.full.EditItemOperation;
import com.example.zoostore.api.operations.item.edit.full.EditItemRequest;
import com.example.zoostore.api.operations.item.edit.full.EditItemResponse;
import com.example.zoostore.api.operations.item.find.all.FindAllItemsRequest;
import com.example.zoostore.api.operations.item.find.all.FindAllItemsOperation;
import com.example.zoostore.api.operations.item.find.all.FindAllItemsResponse;
import com.example.zoostore.api.operations.item.find.byid.FindItemByIdRequest;
import com.example.zoostore.api.operations.item.find.byid.FindItemByIdResponse;
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
import com.example.zoostore.api.operations.item.find.byid.FindItemByIdOperation;
import com.example.zoostore.api.operations.item.find.byproductname.FindItemsByProductNameOperation;
import com.example.zoostore.api.operations.item.find.byproductname.FindItemsByProductNameRequest;
import com.example.zoostore.api.operations.item.find.byproductname.FindItemsByProductNameResponse;
import com.example.zoostore.api.operations.item.find.bytag.FindItemsByTagOperation;
import com.example.zoostore.api.operations.item.find.bytag.FindItemsByTagRequest;
import com.example.zoostore.api.operations.item.find.bytag.FindItemsByTagResponse;
import com.example.zoostore.api.operations.item.unarchive.UnArchiveItemOperation;
import com.example.zoostore.api.operations.item.unarchive.UnArchiveItemRequest;
import com.example.zoostore.api.operations.item.unarchive.UnArchiveItemResponse;
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
    private final EditItemOperation editItemOperation;
    private final ArchiveItemOperation archiveItemOperation;
    private final FindItemByIdOperation findItemByIdOperation;
    private final FindAllItemsOperation findAllItemsOperation;
    private final FindItemsByTagOperation findItemsByTagOperation;
    private final FindItemsByProductNameOperation findItemsByProductNameOperation;
    private final UnArchiveItemOperation unArchiveItemOperation;
    //region GET
    @Operation(description = "Finds an item in the database by a given by the user id.",
            summary = "Finds an item by id.")
    @GetMapping("/{request}")
    public ResponseEntity<FindItemByIdResponse> getItemById(@PathVariable @org.hibernate.validator.constraints.UUID String request){
        FindItemByIdRequest build = FindItemByIdRequest.builder()
                .id(UUID.fromString(request))
                .build();

        return new ResponseEntity<>(findItemByIdOperation.process(build), HttpStatus.OK);
    }

    @Operation(description = "Finds all items with a tag id provided by the user with the number of pages and number of items per page for pagination and if he wishes to include archived items.",
            summary = "Finds all items by tag and archive status and then returns them paginated.")
    @GetMapping()
    public ResponseEntity<FindAllItemsResponse> getAllItems(@RequestParam(required = false, defaultValue = "false") Boolean includeArchived,
                                                            @RequestParam(defaultValue = "1") Integer pageNumber,
                                                            @RequestParam(defaultValue = "2") Integer numberOfItemsPerPage,
                                                            @RequestParam(required = false, defaultValue = "") @org.hibernate.validator.constraints.UUID(allowEmpty = true) String tagId) {
        FindAllItemsRequest build = FindAllItemsRequest.builder()
                .shouldIncludeArchivedItems(includeArchived)
                .numberOfItemsPerPage(numberOfItemsPerPage)
                .pageNumber(pageNumber)
                .tagId(UUID.fromString(tagId))
                .build();

        return new ResponseEntity<>(findAllItemsOperation.process(build), HttpStatus.OK);
    }

    @Operation(description = "Finds all items in the database by a provided by the user product name.",
            summary = "Finds all items by product name.")
    @GetMapping("/by-product-name/{request}")
    public ResponseEntity<FindItemsByProductNameResponse> findItemsByProductName(@PathVariable String request,
                                                                                 @RequestParam(defaultValue = "1") Integer pageNumber,
                                                                                 @RequestParam(defaultValue = "2") Integer numberOfItemsPerPage){
        FindItemsByProductNameRequest build = FindItemsByProductNameRequest.builder()
                .pageNumber(pageNumber)
                .numberOfItemsPerPage(numberOfItemsPerPage)
                .productName(request)
                .build();

        return new ResponseEntity<>(findItemsByProductNameOperation.process(build), HttpStatus.OK);
    }

    @Operation(description = "Finds an item in the database by a given by the user id.",
            summary = "Finds items by tag.")
    @GetMapping("/find-by-tag")
    public ResponseEntity<FindItemsByTagResponse> getItemByTagId(@RequestParam(defaultValue = "1") Integer pageNumber,
                                                              @RequestParam(defaultValue = "2") Integer numberOfItemsPerPage,
                                                                 @RequestParam(required = false, defaultValue = "") @org.hibernate.validator.constraints.UUID String tagId){
        FindItemsByTagRequest build = FindItemsByTagRequest.builder()
                .numberOfItemsPerPage(numberOfItemsPerPage)
                .pageNumber(pageNumber)
                .tagId(UUID.fromString(tagId))
                .build();

        return new ResponseEntity<>(findItemsByTagOperation.process(build), HttpStatus.OK);
    }
    //endregion

    //region POST
    @Operation(description = "From the users request creates a new item that does not exist in the database yet.",
            summary = "Creates a new item.")
    @PostMapping("/create")
    public ResponseEntity<CreateNewItemResponse> createItem(@Valid  @RequestBody CreateNewItemRequest request) {
        return new ResponseEntity<>(createNewItemOperation.process(request), HttpStatus.CREATED);
    }
    //endregion

    //region PUT/PATCH
    @Operation(description = "Edits an existing in the database with the given id from the users request.",
            summary = "Edits a product.")
    @PatchMapping("/full")
    public ResponseEntity<EditItemResponse> editItem(@Valid @RequestBody EditItemRequest request) {
        return new ResponseEntity<>(editItemOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database name of the product with the given id from the users request.",
            summary = "Edits a products name.")
    @PatchMapping("/product-name")
    public ResponseEntity<EditItemProductNameResponse> editItemProductName(@Valid @RequestBody EditItemProductNameRequest request) {
        return new ResponseEntity<>(editItemProductNameOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database description of the product with the given id from the users request.",
            summary = "Edits a products description.")
    @PatchMapping("/description")
    public ResponseEntity<EditItemDescriptionResponse> editItemDescription(@Valid @RequestBody EditItemDescriptionRequest request) {
        return new ResponseEntity<>(editItemDescriptionOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database vendor of the product with the given id from the users request.",
            summary = "Edits a products vendor.")
    @PatchMapping("/vendor")
    public ResponseEntity<EditItemVendorResponse> editItemVendor(@Valid @RequestBody EditItemVendorRequest request) {
        return new ResponseEntity<>(editItemVendorOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database urls of the product with the given id from the users request.",
            summary = "Edits a products urls.")
    @PatchMapping("/multimedia")
    public ResponseEntity<EditItemMultimediaURLResponse> editItemMultimediaURL(@Valid @RequestBody EditItemMultimediaURLRequest request) {
        return new ResponseEntity<>(editItemMultimediaURLOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database tag title of the product with the given id from the users request.",
            summary = "Edits a products tag title.")
    @PatchMapping("/tag")
    public ResponseEntity<EditItemTagResponse> editItemTag(@Valid @RequestBody EditItemTagRequest request) {
        return new ResponseEntity<>(editItemTagOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Archives an existing in the database product with the given id from the users request, hiding it from the clients vision.",
            summary = "Archives an item.")
    @PatchMapping("/archive")
    public ResponseEntity<ArchiveItemResponse> archiveItem(@Valid @RequestBody ArchiveItemRequest request) {
        return new ResponseEntity<>(archiveItemOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Archives an existing in the database product with the given id from the users request, hiding it from the clients vision.",
            summary = "Un-archives an item.")
    @PatchMapping("/un-archive")
    public ResponseEntity<UnArchiveItemResponse> unArchiveItem(@Valid @RequestBody UnArchiveItemRequest request) {
        return new ResponseEntity<>(unArchiveItemOperation.process(request), HttpStatus.ACCEPTED);
    }
    //endregion

    //region DELETE
    //endregion
}
