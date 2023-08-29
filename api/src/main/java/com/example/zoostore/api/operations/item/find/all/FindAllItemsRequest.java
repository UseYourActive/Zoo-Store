package com.example.zoostore.api.operations.item.find.all;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class FindAllItemsRequest implements OperationInput {
    private final Boolean shouldIncludeArchivedItems;

    @NotNull(message = "Tag id is required!")
    private final String tagId;

    @NotNull(message = "Page number is required!")
    @Positive(message = "Page number must be a positive number!")
    private final Integer pageNumber;

    @NotNull(message = "Number of items per page is required!")
    @Positive(message = "Number of pages must be a positive number!")
    private final Integer numberOfItemsPerPage;
}
