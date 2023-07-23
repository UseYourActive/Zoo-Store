package com.example.zoostore.api.operations.item.create;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewItemResponse implements OperationResult {
    private UUID itemId;
    private String productName;
    private String description;
    private UUID vendorId;
    private Set<UUID> multimediaIds; // will always be empty because a URL needs an item to be created.
    private Set<UUID> tagIds;
    private boolean isArchived;
}
