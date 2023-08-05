package com.example.zoostore.api.operations.tag.create;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewTagRequest implements OperationInput {
    @NotBlank(message = "Title is required!")
    @Size(min = 1, max = 50, message = "Tag title must be between 1 and 50 characters long to be valid!")
    private String title;
}
