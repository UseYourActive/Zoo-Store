package com.example.zoostore.api.operations.tag.findbyid;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindTagByIdRequest implements OperationInput {
    @NotNull(message = "Tag id is required!")
    private UUID id;
}
