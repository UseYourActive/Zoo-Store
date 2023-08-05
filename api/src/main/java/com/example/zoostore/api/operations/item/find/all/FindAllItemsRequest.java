package com.example.zoostore.api.operations.item.find.all;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllItemsRequest implements OperationInput {
    private Boolean shouldIncludeArchivedItems;

    @NotNull(message = "Tag id is required!")
    private UUID tagId;

    @NotNull(message = "Page number is required!")
    @Positive(message = "Page number must be a positive number!")
    private Integer pageNumber;

    @NotNull(message = "Number of items per page is required!")
    @Positive(message = "Number of pages must be a positive number!")
    private Integer numberOfItemsPerPage;
}
