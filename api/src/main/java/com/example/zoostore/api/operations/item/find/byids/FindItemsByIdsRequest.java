package com.example.zoostore.api.operations.item.find.byids;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
public class FindItemsByIdsRequest implements OperationInput {

    private List<String> ids;
}
