package com.example.zoostore.api.operations.item.find.all;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllItemsResponse implements OperationResult {
    private Set<FindAllItemsInRepo> items;
}
