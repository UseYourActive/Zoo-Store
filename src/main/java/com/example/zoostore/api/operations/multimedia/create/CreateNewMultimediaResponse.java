package com.example.zoostore.api.operations.multimedia.create;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewMultimediaResponse implements OperationResult {
    private UUID itemId;
    private String url;
}
