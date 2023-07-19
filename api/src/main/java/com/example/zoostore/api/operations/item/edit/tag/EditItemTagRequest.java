package com.example.zoostore.api.operations.item.edit.tag;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemTagRequest implements OperationInput {
    private UUID itemId;
    private Set<UUID> tagIds;
}
