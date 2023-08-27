package com.example.zoostore.rest.controllers;

import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaResponse;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.edit.full.EditMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.edit.full.EditMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.edit.full.EditMultimediaResponse;
import com.example.zoostore.api.operations.multimedia.edit.item.EditMultimediaItemOperation;
import com.example.zoostore.api.operations.multimedia.edit.item.EditMultimediaItemRequest;
import com.example.zoostore.api.operations.multimedia.edit.item.EditMultimediaItemResponse;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLOperation;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLRequest;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLResponse;
import com.example.zoostore.api.operations.multimedia.find.all.FindAllMultimediaOperation;
import com.example.zoostore.api.operations.multimedia.find.all.FindAllMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.find.all.FindAllMultimediaResponse;
import com.example.zoostore.api.operations.multimedia.find.byid.FindMultimediaByIdOperation;
import com.example.zoostore.api.operations.multimedia.find.byid.FindMultimediaByIdRequest;
import com.example.zoostore.api.operations.multimedia.find.byid.FindMultimediaByIdResponse;
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
    private final EditMultimediaURLOperation editMultimediaURLOperation;
    private final EditMultimediaOperation editMultimediaOperation;
    private final EditMultimediaItemOperation editMultimediaItemOperation;
    private final FindAllMultimediaOperation findAllMultimediaOperation;
    private final FindMultimediaByIdOperation findMultimediaByIdOperation;

    //region GET
    //@RestExport
    @Operation(description = "Finds all multimedia in the database.",
            summary = "Finds all multimedia in the database.")
    @GetMapping(path = "/")
    public ResponseEntity<FindAllMultimediaResponse> findAllMultimedia() {
        FindAllMultimediaRequest build = FindAllMultimediaRequest.builder().build();
        return new ResponseEntity<>(findAllMultimediaOperation.process(build), HttpStatus.OK);
    }

    //@RestExport
    @Operation(description = "Finds a multimedia in the database by a given by the user id.",
            summary = "Finds a multimedia by id.")
    @GetMapping(path = "/{multimediaId}")
    public ResponseEntity<FindMultimediaByIdResponse> findMultimediaById(@PathVariable @UUID String multimediaId) {
        FindMultimediaByIdRequest build = FindMultimediaByIdRequest.builder()
                .id(multimediaId)
                .build();
        return new ResponseEntity<>(findMultimediaByIdOperation.process(build), HttpStatus.OK);
    }
    //endregion

    //region POST
    //@RestExport
    @Operation(description = "From the users request creates a new url that does not exist in the database yet.",
            summary = "Creates a new URL.")
    @PostMapping(path = "/create")
    public ResponseEntity<CreateNewMultimediaResponse> createMultimedia(@Valid @RequestBody CreateNewMultimediaRequest request) {
        return new ResponseEntity<>(createNewMultimediaOperation.process(request), HttpStatus.CREATED);
    }
    //endregion

    //region PUT/PATCH
    //@RestExport
    @Operation(description = "Edits the entire multimedia provided by the users information input.",
            summary = "Edits an existing multimedia.")
    @PatchMapping(path = "/full")
    public ResponseEntity<EditMultimediaResponse> editMultimedia(@Valid @RequestBody EditMultimediaRequest request) {
        return new ResponseEntity<>(editMultimediaOperation.process(request), HttpStatus.ACCEPTED);
    }

    //@RestExport
    @Operation(description = "Provided by the user item id replaces the existing in the multimedia one if the item exists.",
            summary = "Edits an existing multimedia item.")
    @PatchMapping(path = "/item")
    public ResponseEntity<EditMultimediaItemResponse> editMultimediaItem(@Valid @RequestBody EditMultimediaItemRequest request) {
        return new ResponseEntity<>(editMultimediaItemOperation.process(request), HttpStatus.ACCEPTED);
    }

    //@RestExport
    @Operation(description = "Replaces an existing in the database urls with another with the given id from the users request.",
            summary = "Edits existing urls.")
    @PatchMapping(path = "/url")
    public ResponseEntity<EditMultimediaURLResponse> editMultimediaURl(@Valid @RequestBody EditMultimediaURLRequest request) {
        return new ResponseEntity<>(editMultimediaURLOperation.process(request), HttpStatus.ACCEPTED);
    }
    //endregion

    //region DELETE
    //endregion
}
