package com.example.zoostore.api.operations.vendor.edit.phone;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorPhoneRequest {
    private UUID id;
    private String phone;
}
