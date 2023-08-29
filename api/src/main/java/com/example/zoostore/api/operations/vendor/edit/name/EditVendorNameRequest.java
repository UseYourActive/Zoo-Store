package com.example.zoostore.api.operations.vendor.edit.name;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class EditVendorNameRequest implements OperationInput {
    @NotNull(message = "Vendor UUID is required!")
    private final String vendorId;

    @NotBlank(message = "Name is required!")
    @Size(min = 1, max = 50, message = "Vendor name must be between 1 and 50 characters long to be valid!")
    private final String name;
}
