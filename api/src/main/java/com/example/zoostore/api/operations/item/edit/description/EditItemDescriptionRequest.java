package com.example.zoostore.api.operations.item.edit.description;

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
public class EditItemDescriptionRequest implements OperationInput {
    @NotNull(message = "Item id is required!")
    private UUID itemId;

    @NotBlank(message = "Description is required!")
    @Size(min = 10, max = 100, message = "Description must be between 10 and 100 characters long to be valid!")
    private String description;
}
