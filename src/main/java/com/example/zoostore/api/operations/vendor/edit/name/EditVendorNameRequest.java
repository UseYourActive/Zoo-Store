package com.example.zoostore.api.operations.vendor.edit.name;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditVendorNameRequest {
    private UUID id;
    private String name;
}
