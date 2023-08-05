package com.example.zoostore.api.operations.tag.find.byid;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindTagByIdResponse implements OperationResult {
    private UUID id;
    private String title;
    private Set<UUID> itemIds;
}
