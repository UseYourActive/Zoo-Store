package com.example.zoostore.api.operations.multimedia.edit.full;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditMultimediaResponse implements OperationResult {
    private String id;
    private String url;
    private String itemId;
}
