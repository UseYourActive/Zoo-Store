package com.example.zoostore.api.operations.vendor.create;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class CreateNewVendorRequest implements OperationInput {
    @NotBlank(message = "Name is required!")
    private final String name;

    @NotBlank(message = "Phone is required!")
    @Size(min = 1, max = 20, message = "Phone number must be between 1 and 20 characters long to be valid!")
    private final String phone;
}
