package com.example.zoostore.api.operations.item.find.bytag;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindItemsByTagResponse implements OperationResult {
    private List<FindItemsByTagInRepo> items;
}
