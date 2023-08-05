package com.example.zoostore.rest.controllers;

import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaResponse;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLRequest;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLResponse;
import com.example.zoostore.api.operations.multimedia.findall.FindAllMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.findall.FindAllMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.findall.FindAllMultimediaResponse;
import com.example.zoostore.api.operations.multimedia.findbyid.FindMultimediaByIdOperation;
import com.example.zoostore.api.operations.multimedia.findbyid.FindMultimediaByIdRequest;
import com.example.zoostore.api.operations.multimedia.findbyid.FindMultimediaByIdResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/multimedia")
public class MultimediaController {
    private final CreateNewMultimediaOperation createNewMultimediaOperation;
    private final EditMultimediaOperation editMultimediaOperation;
    private final FindAllMultimediaOperation findAllMultimediaOperation;
    private final FindMultimediaByIdOperation findMultimediaByIdOperation;

    //region GET
    @Operation(description = "Finds all multimedia in the database.",
            summary = "Finds all multimedia in the database.")
    @GetMapping()
    public ResponseEntity<FindAllMultimediaResponse> findAllMultimedia() {
        FindAllMultimediaRequest build = FindAllMultimediaRequest.builder().build();
        return new ResponseEntity<>(findAllMultimediaOperation.process(build), HttpStatus.OK);
    }

    @Operation(description = "Finds a multimedia in the database by a given by the user id.",
            summary = "Finds a multimedia by id.")
    @GetMapping("/{multimediaId}")
    public ResponseEntity<FindMultimediaByIdResponse> findMultimediaById(@PathVariable @UUID String multimediaId) {
        FindMultimediaByIdRequest build = FindMultimediaByIdRequest.builder()
                .id(java.util.UUID.fromString(multimediaId))
                .build();
        return new ResponseEntity<>(findMultimediaByIdOperation.process(build), HttpStatus.OK);
    }
    //endregion

    //region POST
    @Operation(description = "From the users request creates a new url that does not exist in the database yet.",
            summary = "Creates a new URL.")
    @PostMapping("/create")
    public ResponseEntity<CreateNewMultimediaResponse> createMultimedia(@Valid @RequestBody CreateNewMultimediaRequest request) {
        return new ResponseEntity<>(createNewMultimediaOperation.process(request), HttpStatus.CREATED);
    }
    //endregion

    //region PUT/PATCH
    @Operation(description = "Replaces an existing in the database urls with another with the given id from the users request.",
            summary = "Edits existing urls.")
    @PatchMapping("/url")
    public ResponseEntity<EditMultimediaURLResponse> editMultimediaURl(@Valid @RequestBody EditMultimediaURLRequest request) {
        return new ResponseEntity<>(editMultimediaOperation.process(request), HttpStatus.ACCEPTED);
    }
    //endregion

    //region DELETE
    //endregion
}
