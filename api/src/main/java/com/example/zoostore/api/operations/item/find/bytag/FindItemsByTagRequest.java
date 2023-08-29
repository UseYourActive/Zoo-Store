package com.example.zoostore.api.operations.item.find.bytag;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class FindItemsByTagRequest implements OperationInput {
    @NotNull(message = "Tag id is required!")
    private final String tagId;

    @Positive
    private final Integer pageNumber;

    @Positive
    private final Integer numberOfItemsPerPage;
}
