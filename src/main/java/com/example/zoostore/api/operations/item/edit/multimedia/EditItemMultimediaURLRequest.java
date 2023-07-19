package com.example.zoostore.api.operations.item.edit.multimedia;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemMultimediaURLRequest implements OperationInput {
    private UUID itemId;
    private Set<String> urls;
}
