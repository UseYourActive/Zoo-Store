package com.example.zoostore.api.operations.item.edit.multimedia;

import com.example.zoostore.api.operations.OperationInput;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemMultimediaURLRequest implements OperationInput {
    private UUID id;
    private Set<String> url;
}
