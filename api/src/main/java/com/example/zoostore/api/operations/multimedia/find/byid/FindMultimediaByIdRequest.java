package com.example.zoostore.api.operations.multimedia.find.byid;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class FindMultimediaByIdRequest implements OperationInput {
    @NotNull(message = "Multimedia id is required!")
    private final String id;
}
