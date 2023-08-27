package com.example.zoostore.api.operations.multimedia.edit.url;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditMultimediaURLResponse implements OperationResult {
    private String id;
    private String url;
    private String itemId;
}
