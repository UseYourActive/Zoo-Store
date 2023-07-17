package com.example.zoostore.api.operations.tag.edit.tag;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditTagNameRequest implements OperationInput {
    private UUID id;
    private String title;
}
