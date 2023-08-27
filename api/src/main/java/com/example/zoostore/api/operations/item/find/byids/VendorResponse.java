package com.example.zoostore.api.operations.item.find.byids;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
public class VendorResponse implements OperationResult {

    private String id;

    private String name;

    private String phoneNumber;
}
