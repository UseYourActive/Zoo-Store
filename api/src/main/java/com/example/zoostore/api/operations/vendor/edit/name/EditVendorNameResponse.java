package com.example.zoostore.api.operations.vendor.edit.name;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorNameResponse implements OperationResult {
    private String id;
    private String name;
    private String phone;
    private List<String> itemIds;
}
