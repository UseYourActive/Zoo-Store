package com.example.zoostore.api.operations.item.create;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewItemRequest {
    private String title;
    private String description;
    private UUID vendor;
    private Set<UUID> tags;
}
//@NotBlank // da ne e prazno