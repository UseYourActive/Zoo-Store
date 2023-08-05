package com.example.zoostore.api.operations.vendor.full;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorRequest implements OperationInput {
    @NotNull(message = "Vendor id is required!")
    private UUID id;

    @NotBlank(message = "Vendor name is required!")
    @Size(min = 1, max = 50, message = "Vendor name must be between 1 and 50 characters long to be valid!")
    private String name;

    @NotBlank(message = "Vendor phone is required!")
    @Size(min = 1, max = 50, message = "Vendor phone number must be between 1 and 50 characters long to be valid!")
    private String phone;

    @org.hibernate.validator.constraints.UUID
    private Set<UUID> items;
}
