package com.example.zoostore.controllers;

import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaResponse;
import com.example.zoostore.api.operations.multimedia.create.AddMultimediaService;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaService;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLRequest;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLResponse;
import com.example.zoostore.exceptions.item.ItemNotFoundInRepositoryException;
import com.example.zoostore.exceptions.multimedia.MultimediaNotFoundInRepositoryException;
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
    public ResponseEntity<CreateNewMultimediaResponse> createMultimedia(@RequestBody CreateNewMultimediaRequest request) throws ItemNotFoundInRepositoryException {
        return new ResponseEntity<>(addMultimediaService.addMultimedia(request), HttpStatus.CREATED);
    }

    @Operation(description = "Replaces an existing in the database urls with another with the given id from the users request.", summary = "Edits existing urls.")
    @PatchMapping("/edit/url")
    public ResponseEntity<EditMultimediaURLResponse> editMultimediaURl(@RequestBody EditMultimediaURLRequest request) throws ItemNotFoundInRepositoryException, MultimediaNotFoundInRepositoryException {
        return new ResponseEntity<>(editMultimediaService.editMultimediaURL(request), HttpStatus.ACCEPTED);
    }
}
