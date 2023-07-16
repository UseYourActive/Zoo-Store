package com.example.zoostore.api.operations.item.edit.vendor;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemVendorRequest {
    private UUID id;
    private UUID vendor;
}
