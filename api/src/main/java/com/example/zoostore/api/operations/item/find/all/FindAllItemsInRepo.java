package com.example.zoostore.api.operations.item.find.all;

import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllItemsInRepo {
    private UUID itemId;
    private String productName;
    private String description;
    private UUID vendorId;
    private List<UUID> multimediaIds;
    private List<UUID> tagIds;
    private Boolean isArchived;
}
