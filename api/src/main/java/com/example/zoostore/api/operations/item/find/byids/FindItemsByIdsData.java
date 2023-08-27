package com.example.zoostore.api.operations.item.find.byids;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
public class FindItemsByIdsData implements OperationResult {

    private String id;

    private String title;

    private VendorResponse vendor;
}
