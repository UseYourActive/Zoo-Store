package com.example.zoostore.api.operations.vendor.edit.phone;

import com.example.zoostore.api.operations.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorPhoneRequest implements OperationInput {
    private UUID id;
    private String phone;
}
