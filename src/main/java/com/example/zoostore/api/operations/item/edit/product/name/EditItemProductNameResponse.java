package com.example.zoostore.api.operations.item.edit.product.name;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemProductNameResponse implements OperationResult {
    private UUID itemId;
    private String productName;
}
