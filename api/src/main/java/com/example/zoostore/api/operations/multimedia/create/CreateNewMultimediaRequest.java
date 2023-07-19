package com.example.zoostore.api.operations.multimedia.create;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewMultimediaRequest implements OperationInput {
    private UUID itemId;
    private String url;
}
