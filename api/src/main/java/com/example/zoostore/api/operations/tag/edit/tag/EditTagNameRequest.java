package com.example.zoostore.api.operations.tag.edit.tag;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditTagNameRequest implements OperationInput {
    @NotNull(message = "Tag UUID is required!")
    private UUID tagId;

    @NotBlank(message = "Title is required!")
    @Size(min = 1, max = 50, message = "Tag title must be between 1 and 50 characters long to be valid!")
    private String title;
}
