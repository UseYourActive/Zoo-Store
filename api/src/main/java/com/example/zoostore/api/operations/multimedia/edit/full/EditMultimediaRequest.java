package com.example.zoostore.api.operations.multimedia.edit.full;

import com.example.zoostore.api.base.OperationInput;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class EditMultimediaRequest implements OperationInput {
    @NotNull(message = "Multimedia id is required!")
    private final String multimediaId;

    @Nullable
    private final String url;

    @Nullable
    private final String itemId;
}
