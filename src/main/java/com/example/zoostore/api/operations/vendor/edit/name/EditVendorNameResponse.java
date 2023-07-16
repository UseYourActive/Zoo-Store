package com.example.zoostore.api.operations.vendor.edit.name;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorNameResponse {
    private UUID id;
    private String name;
    private String phone;
}
