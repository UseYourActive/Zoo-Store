package com.example.zoostore.api.operations.item.find.byids;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
public class FindItemsByIdsResponse implements OperationResult {

    private List<FindItemsByIdsData> items;
}
