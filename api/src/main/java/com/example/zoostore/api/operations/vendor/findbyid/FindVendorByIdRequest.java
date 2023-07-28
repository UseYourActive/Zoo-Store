package com.example.zoostore.api.operations.vendor.findbyid;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindVendorByIdRequest implements OperationInput {
    private UUID id;
}
