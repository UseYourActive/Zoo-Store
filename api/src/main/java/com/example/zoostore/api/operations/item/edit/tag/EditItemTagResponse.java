package com.example.zoostore.api.operations.item.edit.tag;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemTagResponse implements OperationResult {
    private UUID itemId;
    private String tagTitle;
}
