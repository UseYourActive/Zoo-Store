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
import com.example.zoostore.api.operations.vendor.findall.FindAllVendorsOperation;
import com.example.zoostore.api.operations.vendor.findall.FindAllVendorsRequest;
import com.example.zoostore.api.operations.vendor.findall.FindAllVendorsResponse;
import com.example.zoostore.api.operations.vendor.findbyid.FindVendorByIdOperation;
import com.example.zoostore.api.operations.vendor.findbyid.FindVendorByIdRequest;
import com.example.zoostore.api.operations.vendor.findbyid.FindVendorByIdResponse;
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
    private final FindAllVendorsOperation findAllVendorsOperation;
    private final FindVendorByIdOperation findVendorByIdOperation;

    //region GET
    @GetMapping()
    public ResponseEntity<FindAllVendorsResponse> findAllVendors() {
        FindAllVendorsRequest build = FindAllVendorsRequest.builder().build();
        return new ResponseEntity<>(findAllVendorsOperation.process(build), HttpStatus.OK);
    }

    @GetMapping("/{vendorId}")
    public ResponseEntity<FindVendorByIdResponse> findVendorById(@PathVariable @UUID String vendorId) {
        FindVendorByIdRequest build = FindVendorByIdRequest.builder()
                .id(java.util.UUID.fromString(vendorId))
                .build();
        return new ResponseEntity<>(findVendorByIdOperation.process(build), HttpStatus.OK);
    }
    //endregion

    //region POST
    @Operation(description = "From the users request creates a new vendor that does not exist in the database yet.",
            summary = "Creates a new tag.")
    @PostMapping("/create")
    public ResponseEntity<CreateNewVendorResponse> createVendor(@Valid @RequestBody CreateNewVendorRequest request) {
        return new ResponseEntity<>(createNewVendorOperation.process(request), HttpStatus.CREATED);
    }
    //endregion

    //region PUT/PATCH
    @Operation(description = "Edits an existing in the database vendor name with the given id from the users request.",
            summary = "Edits a vendors name.")
    @PatchMapping("/name")
    public ResponseEntity<EditVendorNameResponse> editVendorName(@Valid @RequestBody EditVendorNameRequest request) {
        return new ResponseEntity<>(editVendorNameOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database vendor phone with the given id from the users request.",
            summary = "Edits a vendors phone.")
    @PatchMapping("/phone")
    public ResponseEntity<EditVendorPhoneResponse> editVendorPhone(@Valid @RequestBody EditVendorPhoneRequest request) {
        return new ResponseEntity<>(editVendorPhoneOperation.process(request), HttpStatus.ACCEPTED);
    }
    //endregion

    //region DELETE
    //endregion
}
