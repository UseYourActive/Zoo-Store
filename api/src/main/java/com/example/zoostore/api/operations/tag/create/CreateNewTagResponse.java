package com.example.zoostore.api.operations.tag.create;

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
public class CreateNewTagResponse implements OperationResult {
    private String tagId;
    private String title;
    private List<String> itemIds;
}
