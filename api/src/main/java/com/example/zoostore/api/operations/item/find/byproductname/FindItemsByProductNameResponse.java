package com.example.zoostore.api.operations.item.find.byproductname;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindItemsByProductNameResponse implements OperationResult {
    private List<FindItemsByProductNamesRepo> items;
}
