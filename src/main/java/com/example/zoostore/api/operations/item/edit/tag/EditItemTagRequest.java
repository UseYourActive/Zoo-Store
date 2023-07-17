package com.example.zoostore.api.operations.item.edit.tag;

import com.example.zoostore.api.operations.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemTagRequest implements OperationInput {
    private UUID id;
    private String title;
}
