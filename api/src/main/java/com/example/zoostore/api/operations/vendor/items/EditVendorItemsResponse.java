package com.example.zoostore.api.operations.vendor.items;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorItemsResponse implements OperationResult {
    private UUID id;
    private String name;
    private String phone;
    private Set<UUID> itemIds;
}
