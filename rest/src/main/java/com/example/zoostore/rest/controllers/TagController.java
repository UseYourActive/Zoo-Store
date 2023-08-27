package com.example.zoostore.rest.controllers;

import com.example.zoostore.api.operations.tag.create.CreateNewTagRequest;
import com.example.zoostore.api.operations.tag.create.CreateNewTagResponse;
import com.example.zoostore.api.operations.tag.create.CreateNewTagOperation;
import com.example.zoostore.api.operations.tag.edit.title.EditTagTitleRequest;
import com.example.zoostore.api.operations.tag.edit.title.EditTagTitleResponse;
import com.example.zoostore.api.operations.tag.edit.title.EditTagTitleOperation;
import com.example.zoostore.api.operations.tag.find.all.FindAllTagsOperation;
import com.example.zoostore.api.operations.tag.find.all.FindAllTagsRequest;
import com.example.zoostore.api.operations.tag.find.all.FindAllTagsResponse;
import com.example.zoostore.api.operations.tag.find.byid.FindTagByIdOperation;
import com.example.zoostore.api.operations.tag.find.byid.FindTagByIdRequest;
import com.example.zoostore.api.operations.tag.find.byid.FindTagByIdResponse;
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
    private final EditTagTitleOperation editTagOperation;
    private final FindAllTagsOperation findAllTagsOperation;
    private final FindTagByIdOperation findTagByIdOperation;

    //region GET
    //@RestExport
    @Operation(description = "Finds all tags in the database.",
            summary = "Finds all tags in the database.")
    @GetMapping(path = "/")
    public ResponseEntity<FindAllTagsResponse> findAllTags() {
        FindAllTagsRequest build = FindAllTagsRequest.builder().build();
        return new ResponseEntity<>(findAllTagsOperation.process(build), HttpStatus.OK);
    }

    //@RestExport
    @Operation(description = "Finds a tag in the database by a given by the user id.",
            summary = "Finds a tag by id.")
    @GetMapping(path = "/{tagId}")
    public ResponseEntity<FindTagByIdResponse> findTagById(@PathVariable @UUID String tagId) {
        FindTagByIdRequest build = FindTagByIdRequest.builder()
                .id(tagId)
                .build();
        return new ResponseEntity<>(findTagByIdOperation.process(build), HttpStatus.OK);
    }
    //endregion

    //region POST
    //@RestExport
    @Operation(description = "From the users request creates a new tag that does not exist in the database yet.",
            summary = "Creates a new tag.")
    @PostMapping(path = "/create")
    public ResponseEntity<CreateNewTagResponse> createNewTag(@Valid  @RequestBody CreateNewTagRequest request) {
        return new ResponseEntity<>(createNewTagOperation.process(request), HttpStatus.CREATED);
    }
    //endregion

    //region PUT/PATCH
    //@RestExport
    @Operation(description = "Edits an existing in the database tag title with the given id from the users request.",
            summary = "Edits a tag name.")
    @PatchMapping(path = "/name")
    public ResponseEntity<EditTagTitleResponse> editTagName(@Valid @RequestBody EditTagTitleRequest request) {
        return new ResponseEntity<>(editTagOperation.process(request), HttpStatus.ACCEPTED);
    }
    //endregion

    //region DELETE
    //endregion
}
