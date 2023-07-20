package com.example.zoostore.api.operations.item.edit.tag;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemTagRequest implements OperationInput {
    @NotBlank(message = "Was not a valid input!")
    private UUID itemId;

    @NotEmpty(message = "Was not a valid input!")
    private Set<UUID> tagIds;
}
