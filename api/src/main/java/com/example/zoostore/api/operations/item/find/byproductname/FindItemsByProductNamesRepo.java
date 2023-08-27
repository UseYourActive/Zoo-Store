package com.example.zoostore.api.operations.item.find.byproductname;

import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindItemsByProductNamesRepo {
    private String itemId;
    private String productName;
    private String description;
    private String vendorId;
    private List<String> multimediaIds;
    private List<String> tagIds;
    private Boolean isArchived;
}
