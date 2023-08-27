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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorRequest implements OperationInput {
    @NotNull(message = "Vendor id is required!")
    private String id;

    @Nullable
    private String name;

    @Nullable
    private String phone;

    @Nullable
    private List<String> items;
}
