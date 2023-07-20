package com.example.zoostore.api.operations.item.archive;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ArchiveItemRequest implements OperationInput {
    @NotBlank(message = "Item UUID is required!")
    private UUID itemId;
}
