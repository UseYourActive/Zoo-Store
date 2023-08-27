package com.example.zoostore.api.operations.vendor.find.byid;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindVendorByIdRequest implements OperationInput {
    @NotNull(message = "Vendor id is required!")
    private String id;
}
