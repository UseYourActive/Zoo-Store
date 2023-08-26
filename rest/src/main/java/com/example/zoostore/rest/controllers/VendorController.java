package com.example.zoostore.rest.controllers;

import com.example.zoostore.api.operations.vendor.create.CreateNewVendorRequest;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorResponse;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorOperation;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameRequest;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameResponse;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameOperation;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneRequest;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneResponse;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneOperation;
import com.example.zoostore.api.operations.vendor.find.all.FindAllVendorsOperation;
import com.example.zoostore.api.operations.vendor.find.all.FindAllVendorsRequest;
import com.example.zoostore.api.operations.vendor.find.all.FindAllVendorsResponse;
import com.example.zoostore.api.operations.vendor.find.byid.FindVendorByIdOperation;
import com.example.zoostore.api.operations.vendor.find.byid.FindVendorByIdRequest;
import com.example.zoostore.api.operations.vendor.find.byid.FindVendorByIdResponse;
import com.example.zoostore.api.operations.vendor.edit.full.EditVendorOperation;
import com.example.zoostore.api.operations.vendor.edit.full.EditVendorRequest;
import com.example.zoostore.api.operations.vendor.edit.full.EditVendorResponse;
import com.example.zoostore.api.operations.vendor.edit.items.EditVendorItemsOperation;
import com.example.zoostore.api.operations.vendor.edit.items.EditVendorItemsRequest;
import com.example.zoostore.api.operations.vendor.edit.items.EditVendorItemsResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/vendors")
public class VendorController {
    private final CreateNewVendorOperation createNewVendorOperation;
    private final EditVendorNameOperation editVendorNameOperation;
    private final EditVendorPhoneOperation editVendorPhoneOperation;
    private final EditVendorItemsOperation editVendorItemsOperation;
    private final EditVendorOperation editVendorOperation;
    private final FindAllVendorsOperation findAllVendorsOperation;
    private final FindVendorByIdOperation findVendorByIdOperation;

    //region GET
    //@RestExport
    @Operation(description = "Finds all vendors in the database.",
            summary = "Finds all vendors in the database.")
    @GetMapping(path = "/")
    public ResponseEntity<FindAllVendorsResponse> findAllVendors() {
        FindAllVendorsRequest build = FindAllVendorsRequest.builder().build();
        return new ResponseEntity<>(findAllVendorsOperation.process(build), HttpStatus.OK);
    }

    //@RestExport
    @Operation(description = "Finds a vendor in the database by a given by the user id.",
            summary = "Finds a vendor by id.")
    @GetMapping(path = "/{vendorId}")
    public ResponseEntity<FindVendorByIdResponse> findVendorById(@PathVariable @UUID String vendorId) {
        FindVendorByIdRequest build = FindVendorByIdRequest.builder()
                .id(java.util.UUID.fromString(vendorId))
                .build();
        return new ResponseEntity<>(findVendorByIdOperation.process(build), HttpStatus.OK);
    }
    //endregion

    //region POST
    //@RestExport
    @Operation(description = "From the users request creates a new vendor that does not exist in the database yet.",
            summary = "Creates a new tag.")
    @PostMapping(path = "/create")
    public ResponseEntity<CreateNewVendorResponse> createVendor(@Valid @RequestBody CreateNewVendorRequest request) {
        return new ResponseEntity<>(createNewVendorOperation.process(request), HttpStatus.CREATED);
    }
    //endregion

    //region PUT/PATCH
    //@RestExport
    @Operation(description = "Edits an existing in the database vendor with the given id from the users request.",
            summary = "Edits a vendor.")
    @PatchMapping(path = "/full")
    public ResponseEntity<EditVendorResponse> editVendor(@Valid @RequestBody EditVendorRequest request) {
        return new ResponseEntity<>(editVendorOperation.process(request), HttpStatus.ACCEPTED);
    }

    //@RestExport
    @Operation(description = "Edits an existing in the database vendor name with the given id from the users request.",
            summary = "Edits a vendors name.")
    @PatchMapping(path = "/name")
    public ResponseEntity<EditVendorNameResponse> editVendorName(@Valid @RequestBody EditVendorNameRequest request) {
        return new ResponseEntity<>(editVendorNameOperation.process(request), HttpStatus.ACCEPTED);
    }

    //@RestExport
    @Operation(description = "Edits an existing in the database vendor phone with the given id from the users request.",
            summary = "Edits a vendors phone.")
    @PatchMapping(path = "/phone")
    public ResponseEntity<EditVendorPhoneResponse> editVendorPhone(@Valid @RequestBody EditVendorPhoneRequest request) {
        return new ResponseEntity<>(editVendorPhoneOperation.process(request), HttpStatus.ACCEPTED);
    }

    //@RestExport
    @Operation(description = "Edits an existing in the database vendor phone with the given id from the users request.",
            summary = "Edits a vendors phone.")
    @PatchMapping(path = "/items")
    public ResponseEntity<EditVendorItemsResponse> editVendorItems(@Valid @RequestBody EditVendorItemsRequest request) {
        return new ResponseEntity<>(editVendorItemsOperation.process(request), HttpStatus.ACCEPTED);
    }
    //endregion

    //region DELETE
    //endregion
}
