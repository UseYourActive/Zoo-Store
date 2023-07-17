package com.example.zoostore.api.operations.tag.edit.tag;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditTagNameResponse implements OperationResult {
    private UUID id;
    private String title;
}
