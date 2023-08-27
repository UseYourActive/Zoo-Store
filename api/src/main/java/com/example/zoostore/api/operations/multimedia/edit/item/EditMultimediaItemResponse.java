package com.example.zoostore.api.operations.multimedia.edit.item;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditMultimediaItemResponse implements OperationResult {
    private String id;
    private String url;
    private String itemId;
}
