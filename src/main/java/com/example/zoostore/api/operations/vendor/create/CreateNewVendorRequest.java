package com.example.zoostore.api.operations.vendor.create;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewVendorRequest {
    private String name;
    private String phone;
}
