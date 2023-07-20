package com.example.zoostore.api.operations.item.edit.tag;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemTagRequest implements OperationInput {
    @NotNull(message = "Item UUID is required!")
    private UUID itemId;

    @NotNull(message = "Tags UUIDs are required!")
    private Set<UUID> tagIds;
}
