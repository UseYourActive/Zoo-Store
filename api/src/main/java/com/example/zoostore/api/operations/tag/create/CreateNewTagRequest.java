package com.example.zoostore.api.operations.tag.create;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewTagRequest implements OperationInput {
    private String title;
}
