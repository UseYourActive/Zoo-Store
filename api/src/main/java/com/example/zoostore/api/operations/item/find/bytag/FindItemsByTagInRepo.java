package com.example.zoostore.api.operations.item.find.bytag;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindItemsByTagInRepo {
    private String itemId;
    private String productName;
    private String description;
    private String vendorId;
    private List<String> multimediaIds;
    private List<String> tagIds;
    private Boolean isArchived;
}
