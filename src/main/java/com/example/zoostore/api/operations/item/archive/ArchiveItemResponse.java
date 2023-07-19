package com.example.zoostore.api.operations.item.archive;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ArchiveItemResponse implements OperationResult {
    private UUID itemId;
    private String title;
    private String description;
    private UUID vendorId;
    private Set<UUID> multimediaIds;
    private Set<UUID> tagIds;
    private boolean isArchived;
}
