package com.example.zoostore.api.operations.item.unarchive;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class UnArchiveItemRequest implements OperationInput {
    @NotNull(message = "Item id is required!")
    private final String id;
}
