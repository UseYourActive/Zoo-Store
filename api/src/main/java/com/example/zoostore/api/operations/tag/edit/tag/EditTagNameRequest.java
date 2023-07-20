package com.example.zoostore.api.operations.tag.edit.tag;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String title;
}
