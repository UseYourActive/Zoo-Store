package com.example.zoostore.api.operations.vendor.find.all;

import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllVendorsInRepo {
    private String id;
    private String name;
    private String phone;
    private List<String> itemIds;
}
