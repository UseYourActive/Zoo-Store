package com.example.zoostore.controllers;

import com.example.zoostore.api.operations.vendor.create.CreateNewVendorRequest;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorResponse;
import com.example.zoostore.api.operations.vendor.create.AddVendorService;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameRequest;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameResponse;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneRequest;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneResponse;
import com.example.zoostore.api.operations.vendor.edit.EditVendorService;
import com.example.zoostore.exceptions.vendor.VendorNotFoundInRepositoryException;
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
    private final AddVendorService addVendorService;
    private final EditVendorService editVendorService;

    @Operation(description = "From the users request creates a new vendor that does not exist in the database yet.", summary = "Creates a new tag.")
    @PostMapping("/create")
    public ResponseEntity<CreateNewVendorResponse> createVendor(@RequestBody CreateNewVendorRequest request){
        return new ResponseEntity<>(addVendorService.addVendor(request), HttpStatus.CREATED);
    }

    @Operation(description = "Edits an existing in the database vendor name with the given id from the users request.", summary = "Edits a vendors name.")
    @PatchMapping("/edit/name")
    public ResponseEntity<EditVendorNameResponse> editVendorName(@RequestBody EditVendorNameRequest request) throws VendorNotFoundInRepositoryException {
        return new ResponseEntity<>(editVendorService.editVendorName(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "Edits an existing in the database vendor phone with the given id from the users request.", summary = "Edits a vendors phone.")
    @PatchMapping("/edit/phone")
    public ResponseEntity<EditVendorPhoneResponse> editVendorPhone(@RequestBody @Valid EditVendorPhoneRequest request) throws VendorNotFoundInRepositoryException {
        return new ResponseEntity<>(editVendorService.editVendorPhone(request), HttpStatus.ACCEPTED);
    }
}
