package com.example.zoostore.api.operations.item.archive;

import com.example.zoostore.api.operations.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ArchiveItemRequest implements OperationInput {
    private UUID id;
}
