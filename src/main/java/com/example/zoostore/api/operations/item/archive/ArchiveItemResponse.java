package com.example.zoostore.api.operations.item.archive;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ArchiveItemResponse {
    private UUID id;
    private String title;
    private String description;
    private UUID vendor;
    private Set<UUID> multimedia;
    private Set<UUID> tags;
    private boolean isArchived;
}
