package com.example.zoostore.api.operations.tag.find.all;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllTagsResponse implements OperationResult {
    private Set<FindAllTagsInRepo> tags;
}
