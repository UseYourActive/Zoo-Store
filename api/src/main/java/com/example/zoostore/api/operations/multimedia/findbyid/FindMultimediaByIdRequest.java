package com.example.zoostore.api.operations.multimedia.findbyid;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindMultimediaByIdRequest implements OperationInput {
    private UUID id;
}
