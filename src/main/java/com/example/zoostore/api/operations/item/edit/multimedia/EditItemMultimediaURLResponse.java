package com.example.zoostore.api.operations.item.edit.multimedia;

import com.example.zoostore.api.operations.OperationResult;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemMultimediaURLResponse implements OperationResult {
    private UUID id;
    private Set<String> multimedia;
}
