package com.example.zoostore.api.operations.item.findall;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllItemsInput implements OperationInput {
    private Boolean shouldIncludeArchivedItems;
    private UUID tagId;
    private Integer pageNumber;
    private Integer numberOfItemsPerPage;
}
