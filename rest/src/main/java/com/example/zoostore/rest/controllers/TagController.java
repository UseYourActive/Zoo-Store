package com.example.zoostore.rest.controllers;

import com.example.zoostore.api.operations.tag.create.CreateNewTagRequest;
import com.example.zoostore.api.operations.tag.create.CreateNewTagResponse;
import com.example.zoostore.api.operations.tag.create.CreateNewTagOperation;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameRequest;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameResponse;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagOperation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tags")
public class TagController {
    private final CreateNewTagOperation createNewTagService;
    private final EditTagOperation editTagService;

    @Operation(description = "From the users request creates a new tag that does not exist in the database yet.",
            summary = "Creates a new tag.")
    @PostMapping("/create")
    public ResponseEntity<CreateNewTagResponse> createNewTag(@Valid  @RequestBody CreateNewTagRequest request) {
        return new ResponseEntity<>(createNewTagService.process(request), HttpStatus.CREATED);
    }

    @Operation(description = "Edits an existing in the database tag title with the given id from the users request.",
            summary = "Edits a tag title.")
    @PatchMapping("/edit/name")
    public ResponseEntity<EditTagNameResponse> editTagName(@Valid @RequestBody EditTagNameRequest request) {
        return new ResponseEntity<>(editTagService.process(request), HttpStatus.ACCEPTED);
    }
}
