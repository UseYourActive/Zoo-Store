package com.example.zoostore.api.operations.item.find.bytag;

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
public class FindItemsByTagRequest implements OperationInput {
    @NotNull(message = "Tag id is required!")
    private String tagId;

    @Positive
    private Integer pageNumber;

    @Positive
    private Integer numberOfItemsPerPage;
}
