package com.example.zoostore.api.operations.item.edit.vendor;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemVendorResponse implements OperationResult {
    private UUID itemId;
    private UUID vendorId;
}
