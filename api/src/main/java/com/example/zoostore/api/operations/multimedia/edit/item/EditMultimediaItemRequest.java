package com.example.zoostore.api.operations.multimedia.edit.item;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class EditMultimediaItemRequest implements OperationInput {
    @NotNull(message = "Multimedia id is required!")
    private final String multimediaId;

    @NotNull(message = "Item id is required!")
    private final String itemId;
}
