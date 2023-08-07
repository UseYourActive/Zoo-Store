package com.example.zoostore.api.operations.item.edit.full;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemResponse implements OperationResult {
    private UUID itemId;
    private String productName;
    private String description;
    private UUID vendorId;
    private List<UUID> multimediaIds;
    private List<UUID> tagIds;
    private Boolean isArchived;
}
