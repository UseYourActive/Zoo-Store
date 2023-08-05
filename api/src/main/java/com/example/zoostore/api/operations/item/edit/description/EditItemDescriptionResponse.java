package com.example.zoostore.api.operations.item.edit.description;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemDescriptionResponse implements OperationResult {
    private UUID itemId;
    private String productName;
    private String description;
    private UUID vendorId;
    private Set<UUID> multimediaIds;
    private Set<UUID> tagIds;
    private Boolean isArchived;
}
