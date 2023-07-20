package com.example.zoostore.api.operations.item.edit.vendor;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemVendorRequest implements OperationInput {
    @NotBlank(message = "Item UUID is required!")
    private UUID itemId;

    @NotBlank(message = "Vendor UUID is required!")
    private UUID vendorId;
}
