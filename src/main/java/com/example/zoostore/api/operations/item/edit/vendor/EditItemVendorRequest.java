package com.example.zoostore.api.operations.item.edit.vendor;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemVendorRequest implements OperationInput {
    private UUID id;
    private UUID vendor;
}
