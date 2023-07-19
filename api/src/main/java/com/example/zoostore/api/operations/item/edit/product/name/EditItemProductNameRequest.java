package com.example.zoostore.api.operations.item.edit.product.name;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemProductNameRequest implements OperationInput {
    private UUID itemId;
    private String productName;
}
