package com.example.zoostore.api.operations.vendor.edit.items;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorItemsRequest implements OperationInput {
    @NotNull(message = "Vendor id is required!")
    private UUID vendorId;

    @org.hibernate.validator.constraints.UUID
    private Set<UUID> itemIds;
}
