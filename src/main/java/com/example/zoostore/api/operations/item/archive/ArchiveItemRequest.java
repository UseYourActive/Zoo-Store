package com.example.zoostore.api.operations.item.archive;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ArchiveItemRequest {
    private UUID id;
}
