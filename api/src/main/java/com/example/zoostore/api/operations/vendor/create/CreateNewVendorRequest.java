package com.example.zoostore.api.operations.vendor.create;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewVendorRequest implements OperationInput {
    @NotBlank(message = "Name is required!")
    private String name;

    @NotBlank(message = "Phone is required!")
    private String phone;
}
