package com.example.zoostore.api.operations.tag.find.byid;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class FindTagByIdRequest implements OperationInput {
    @NotNull(message = "Tag id is required!")
    private final String id;
}
