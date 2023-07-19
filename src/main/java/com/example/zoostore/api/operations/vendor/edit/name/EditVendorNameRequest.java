package com.example.zoostore.api.operations.vendor.edit.name;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorNameRequest implements OperationInput {
    private UUID vendorId;
    private String name;
}
