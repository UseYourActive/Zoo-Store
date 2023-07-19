package com.example.zoostore.api.operations.vendor.create;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewVendorResponse implements OperationResult {
    private UUID vendorId;
    private String name;
    private String phone;
}
