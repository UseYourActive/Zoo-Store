package com.example.zoostore.api.operations.multimedia.edit.full;

import com.example.zoostore.api.base.OperationInput;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditMultimediaRequest implements OperationInput {
    @NotNull(message = "Multimedia id is required!")
    private String multimediaId;

    @Nullable
    private String url;

    @Nullable
    private String itemId;
}
