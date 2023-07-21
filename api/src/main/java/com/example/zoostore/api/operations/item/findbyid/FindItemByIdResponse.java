package com.example.zoostore.api.operations.item.findbyid;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindItemByIdResponse implements OperationResult {
    private UUID id;
    private String productName;
    private String description;
    private UUID vendor;
    private Set<UUID> multimedia;
    private Set<UUID> tags;
    private boolean archived;
}
