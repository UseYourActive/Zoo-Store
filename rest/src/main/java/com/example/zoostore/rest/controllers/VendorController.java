package com.example.zoostore.rest.controllers;

import com.example.zoostore.api.operations.multimedia.findall.FindAllMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.findall.FindAllMultimediaResponse;
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
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vendors")
public class VendorController {
    private final CreateNewVendorOperation createNewVendorOperation;
    private final EditVendorNameOperation editVendorNameOperation;
    private final EditVendorPhoneOperation editVendorPhoneOperation;
    private final FindAllVendorsOperation findAllVendorsOperation;

    @Operation(description = "From the users request creates a new vendor that does not exist in the database yet.",
            summary = "Creates a new tag.")
    @PostMapping("/create")
    public ResponseEntity<CreateNewVendorResponse> createVendor(@Valid @RequestBody CreateNewVendorRequest request) {
        return new ResponseEntity<>(createNewVendorOperation.process(request), HttpStatus.CREATED);
    }

    @Operation(description = "Edits an existing in the database vendor name with the given id from the users request.",
            summary = "Edits a vendors name.")
    @PatchMapping("/edit/name")
    public ResponseEntity<EditVendorNameResponse> editVendorName(@Valid @RequestBody EditVendorNameRequest request) {
        return new ResponseEntity<>(editVendorNameOperation.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database vendor phone with the given id from the users request.",
            summary = "Edits a vendors phone.")
    @PatchMapping("/edit/phone")
    public ResponseEntity<EditVendorPhoneResponse> editVendorPhone(@Valid @RequestBody EditVendorPhoneRequest request) {
        return new ResponseEntity<>(editVendorPhoneOperation.process(request), HttpStatus.ACCEPTED);
    }

    @GetMapping("/find-all")
    public ResponseEntity<FindAllVendorsResponse> findAllVendors(FindAllVendorsRequest request) {
        return new ResponseEntity<>(findAllVendorsOperation.process(request), HttpStatus.OK);
    }
}
