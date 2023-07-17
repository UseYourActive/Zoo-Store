package com.example.zoostore.api.operations.item.edit.description;

import com.example.zoostore.api.operations.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemDescriptionRequest implements OperationInput {
    private UUID id;
    private String description;
}
