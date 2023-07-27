package com.example.zoostore.api.operations.vendor.findall;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllVendorsInRepo {
    private UUID id;
    private String name;
    private String phone;
    private Set<UUID> itemIds;
}
