package com.example.zoostore.api.operations.vendor.edit.full;

import com.example.zoostore.api.base.OperationInput;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class EditVendorRequest implements OperationInput {
    @NotNull(message = "Vendor id is required!")
    private final String id;

    @Nullable
    private final String name;

    @Nullable
    private final String phone;

    @Nullable
    private final List<String> items;
}
