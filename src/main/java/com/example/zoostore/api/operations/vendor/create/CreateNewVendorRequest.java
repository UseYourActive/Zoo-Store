package com.example.zoostore.api.operations.vendor.create;

import com.example.zoostore.api.operations.OperationInput;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewVendorRequest implements OperationInput {
    private String name;
    private String phone;
}
