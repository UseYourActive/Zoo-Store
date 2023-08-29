package com.example.zoostore.api.operations.multimedia.create;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class CreateNewMultimediaRequest implements OperationInput {
    @NotNull(message = "Item UUID is required!")
    private final String itemId;

    @NotBlank(message = "URL is required!")
    @Size(min = 1, max = 300, message = "URL must be between 1 and 300 characters long to be valid!")
    private final String url;
}
