package com.example.zoostore.api.operations.vendor.edit.phone;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorPhoneResponse {
    private UUID id;
    private String name;
    private String phone;
}
