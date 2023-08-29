package com.example.zoostore.api.operations.item.archive;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class ArchiveItemRequest implements OperationInput {
    @NotNull(message = "Item id is required!")
    private final String itemId;
}
