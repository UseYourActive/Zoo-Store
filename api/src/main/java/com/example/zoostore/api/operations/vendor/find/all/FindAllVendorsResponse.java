package com.example.zoostore.api.operations.vendor.find.all;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllVendorsResponse implements OperationResult {
    private List<FindAllVendorsInRepo> vendors;
}
