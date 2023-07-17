package com.example.zoostore.controllers;

import com.example.zoostore.api.operations.tag.create.CreateNewTagRequest;
import com.example.zoostore.api.operations.tag.create.CreateNewTagResponse;
import com.example.zoostore.api.operations.tag.create.AddTagService;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameRequest;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameResponse;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagService;
import com.example.zoostore.exceptions.tag.TagNotFoundInRepositoryException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tags")
public class TagController {
    private final AddTagService addTagService;
    private final EditTagService editTagService;

    @Operation(description = "From the users request creates a new tag that does not exist in the database yet.", summary = "Creates a new tag.")
    @PostMapping("/create")
    public ResponseEntity<CreateNewTagResponse> createTag(@RequestBody CreateNewTagRequest request){
        return new ResponseEntity<>(addTagService.addTag(request), HttpStatus.CREATED);
    }

    @Operation(description = "Edits an existing in the database tag title with the given id from the users request.", summary = "Edits a tag title.")
    @PatchMapping("/edit/name")
    public ResponseEntity<EditTagNameResponse> editTagName(@RequestBody EditTagNameRequest request) throws TagNotFoundInRepositoryException {
        return new ResponseEntity<>(editTagService.editTagName(request), HttpStatus.ACCEPTED);
    }
}
