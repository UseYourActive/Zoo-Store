package com.example.zoostore.rest.controllers;

import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaResponse;
import com.example.zoostore.api.operations.multimedia.create.AddMultimediaService;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaService;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLRequest;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLResponse;
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
@RequestMapping("/multimedia")
public class MultimediaController {
    private final AddMultimediaService addMultimediaService;
    private final EditMultimediaService editMultimediaService;

    @Operation(description = "From the users request creates a new url that does not exist in the database yet.", summary = "Creates a new URL.")
    @PostMapping("/create")
    public ResponseEntity<CreateNewMultimediaResponse> createMultimedia(@RequestBody CreateNewMultimediaRequest request) throws ItemNotFoundInRepositoryException, MultimediaNotFoundInRepositoryException, TagNotFoundInRepositoryException, VendorNotFoundInRepositoryException {
        return new ResponseEntity<>(addMultimediaService.process(request), HttpStatus.CREATED);
    }

    @Operation(description = "Replaces an existing in the database urls with another with the given id from the users request.", summary = "Edits existing urls.")
    @PatchMapping("/edit/url")
    public ResponseEntity<EditMultimediaURLResponse> editMultimediaURl(@RequestBody EditMultimediaURLRequest request) throws ItemNotFoundInRepositoryException, MultimediaNotFoundInRepositoryException, TagNotFoundInRepositoryException, VendorNotFoundInRepositoryException {
        return new ResponseEntity<>(editMultimediaService.process(request), HttpStatus.ACCEPTED);
    }
}
