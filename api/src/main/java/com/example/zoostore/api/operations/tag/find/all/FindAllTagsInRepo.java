package com.example.zoostore.api.operations.tag.find.all;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllTagsInRepo {
    private UUID id;
    private String title;
    private Set<UUID> itemIds;
}
