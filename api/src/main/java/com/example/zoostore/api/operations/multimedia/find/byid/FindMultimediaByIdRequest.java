package com.example.zoostore.api.operations.multimedia.find.byid;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindMultimediaByIdRequest implements OperationInput {
    @NotNull(message = "Multimedia id is required!")
    private String id;
}
