package com.example.zoostore.api.operations.multimedia.findbyid;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindMultimediaByIdResponse implements OperationResult {
    private UUID id;
    private String url;
    private UUID itemId;
}