package com.example.zoostore.api.operations.item.edit.product.name;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemProductNameRequest implements OperationInput {
    @NotBlank(message = "Item UUID is required!")
    private UUID itemId;

    @Size(min = 1, max = 50, message = "Product name must be between 1 and 50 characters long to be valid!")
    private String productName;
}
