package com.example.zoostore.api.operations.vendor.edit.phone;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorPhoneRequest implements OperationInput {
    @NotNull(message = "Vendor UUID is required!")
    private String vendorId;

    @NotBlank(message = "Phone is required!")
    @Size(min = 1, max = 20, message = "Phone number must be between 1 and 20 characters long to be valid!")
    private String phone;
}
