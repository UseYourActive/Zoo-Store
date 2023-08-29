package com.example.zoostore.api.operations.item.edit.product.name;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class EditItemProductNameRequest implements OperationInput {
    @NotNull(message = "Item UUID is required!")
    private final String itemId;

    @NotBlank(message = "Product name is required!")
    @Size(min = 1, max = 50, message = "Product name must be between 1 and 50 characters long to be valid!")
    private final String productName;
}
