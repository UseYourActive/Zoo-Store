package com.example.zoostore.api.operations.item.edit.vendor;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class EditItemVendorRequest implements OperationInput {
    @NotNull(message = "Item id is required!")
    private final String itemId;

    @NotNull(message = "Vendor id is required!")
    private final String vendorId;
}
