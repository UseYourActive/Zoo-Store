package com.example.zoostore.api.operations.vendor.create;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewVendorResponse {
    private UUID id;
    private String name;
    private String phone;
}
