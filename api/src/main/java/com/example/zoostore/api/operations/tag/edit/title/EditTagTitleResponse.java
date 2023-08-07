package com.example.zoostore.api.operations.tag.edit.title;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditTagTitleResponse implements OperationResult {
    private UUID tagId;
    private String title;
    private List<UUID> itemIds;
}
