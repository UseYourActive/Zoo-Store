package com.example.zoostore.api.operations.item.find.byproductname;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class FindItemsByProductNameRequest implements OperationInput {
    @NotBlank(message = "Product name is required!")
    @Size(min = 1, max = 50, message = "Product name must be between 1 and 50 characters long to be valid!")
    private final String productName;

    @NotNull(message = "Page number is required!")
    @Positive(message = "Page number must be a positive number!")
    private final Integer pageNumber;

    @NotNull(message = "Number of items per page is required!")
    @Positive(message = "Number of pages must be a positive number!")
    private final Integer numberOfItemsPerPage;
}
