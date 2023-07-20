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
    @NotBlank(message = "Was not a valid input!")
    private String name;

    @NotBlank(message = "Was not a valid input!")
    private String phone;
}
