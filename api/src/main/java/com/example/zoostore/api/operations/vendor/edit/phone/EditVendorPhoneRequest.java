package com.example.zoostore.api.operations.vendor.edit.phone;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorPhoneRequest implements OperationInput {
    @NotNull(message = "Vendor UUID is required!")
    private UUID vendorId;

    @NotBlank(message = "Phone is required!")
    private String phone;
}
