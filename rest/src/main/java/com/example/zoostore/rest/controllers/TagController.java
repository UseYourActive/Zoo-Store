package com.example.zoostore.rest.controllers;

import com.example.zoostore.api.operations.tag.create.CreateNewTagRequest;
import com.example.zoostore.api.operations.tag.create.CreateNewTagResponse;
import com.example.zoostore.api.operations.tag.create.CreateNewTagOperation;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameRequest;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameResponse;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagOperation;
import com.example.zoostore.api.operations.tag.findall.FindAllTagsOperation;
import com.example.zoostore.api.operations.tag.findall.FindAllTagsRequest;
import com.example.zoostore.api.operations.tag.findall.FindAllTagsResponse;
import com.example.zoostore.api.operations.tag.findbyid.FindTagByIdOperation;
import com.example.zoostore.api.operations.tag.findbyid.FindTagByIdRequest;
import com.example.zoostore.api.operations.tag.findbyid.FindTagByIdResponse;
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
@RequestMapping("/tags")
public class TagController {
    private final CreateNewTagOperation createNewTagOperation;
    private final EditTagOperation editTagOperation;
    private final FindAllTagsOperation findAllTagsOperation;
    private final FindTagByIdOperation findTagByIdOperation;

    //region GET
    @GetMapping()
    public ResponseEntity<FindAllTagsResponse> findAllTags() {
        FindAllTagsRequest build = FindAllTagsRequest.builder().build();
        return new ResponseEntity<>(findAllTagsOperation.process(build), HttpStatus.OK);
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<FindTagByIdResponse> findTagById(@PathVariable @UUID String tagId) {
        FindTagByIdRequest build = FindTagByIdRequest.builder()
                .id(java.util.UUID.fromString(tagId))
                .build();
        return new ResponseEntity<>(findTagByIdOperation.process(build), HttpStatus.OK);
    }
    //endregion

    //region POST
    @Operation(description = "From the users request creates a new tag that does not exist in the database yet.",
            summary = "Creates a new tag.")
    @PostMapping("/create")
    public ResponseEntity<CreateNewTagResponse> createNewTag(@Valid  @RequestBody CreateNewTagRequest request) {
        return new ResponseEntity<>(createNewTagOperation.process(request), HttpStatus.CREATED);
    }
    //endregion

    //region PUT/PATCH
    @Operation(description = "Edits an existing in the database tag title with the given id from the users request.",
            summary = "Edits a tag title.")
    @PatchMapping("/name")
    public ResponseEntity<EditTagNameResponse> editTagName(@Valid @RequestBody EditTagNameRequest request) {
        return new ResponseEntity<>(editTagOperation.process(request), HttpStatus.ACCEPTED);
    }
    //endregion

    //region DELETE
    //endregion
}
