package com.example.zoostore.api.operations.item.create;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class CreateNewItemRequest implements OperationInput {
    @NotBlank(message = "Product name is required!")
    @Size(min = 1, max = 50, message = "Product name must be between 1 and 50 characters long to be valid!")
    private final String productName;

    @NotBlank(message = "Description is required!")
    @Size(min = 10, max = 100, message = "Description must be between 10 and 100 characters long to be valid!")
    private final String description;

    @NotNull(message = "Vendor UUID is required!")
    private final String vendorId;

    @NotNull(message = "Tags UUIDs are required!")
    private final List<String> tagIds;
}
