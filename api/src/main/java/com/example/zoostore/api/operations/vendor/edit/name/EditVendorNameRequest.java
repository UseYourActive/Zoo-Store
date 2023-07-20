package com.example.zoostore.api.operations.vendor.edit.name;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorNameRequest implements OperationInput {
    @NotBlank(message = "Was not a valid input!")
    private UUID vendorId;

    @NotBlank(message = "Was not a valid input!")
    private String name;
}
