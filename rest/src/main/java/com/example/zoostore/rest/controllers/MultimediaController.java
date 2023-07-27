package com.example.zoostore.rest.controllers;

import com.example.zoostore.api.operations.item.findall.FindAllItemsInput;
import com.example.zoostore.api.operations.item.findall.FindAllItemsResult;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaResponse;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLRequest;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLResponse;
import com.example.zoostore.api.operations.multimedia.findall.FindAllMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.findall.FindAllMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.findall.FindAllMultimediaResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/multimedia")
public class MultimediaController {
    private final CreateNewMultimediaOperation createNewMultimediaService;
    private final EditMultimediaOperation editMultimediaService;
    private final FindAllMultimediaOperation findAllMultimediaOperation;

    @Operation(description = "From the users request creates a new url that does not exist in the database yet.",
            summary = "Creates a new URL.")
    @PostMapping("/create")
    public ResponseEntity<CreateNewMultimediaResponse> createMultimedia(@Valid @RequestBody CreateNewMultimediaRequest request) {
        return new ResponseEntity<>(createNewMultimediaService.process(request), HttpStatus.CREATED);
    }

    @Operation(description = "Replaces an existing in the database urls with another with the given id from the users request.",
            summary = "Edits existing urls.")
    @PatchMapping("/edit/url")
    public ResponseEntity<EditMultimediaURLResponse> editMultimediaURl(@Valid @RequestBody EditMultimediaURLRequest request) {
        return new ResponseEntity<>(editMultimediaService.process(request), HttpStatus.ACCEPTED);
    }

    @GetMapping("/find-all")
    public ResponseEntity<FindAllMultimediaResponse> findAllMultimedia(FindAllMultimediaRequest request) {
        return new ResponseEntity<>(findAllMultimediaOperation.process(request), HttpStatus.OK);
    }
}
