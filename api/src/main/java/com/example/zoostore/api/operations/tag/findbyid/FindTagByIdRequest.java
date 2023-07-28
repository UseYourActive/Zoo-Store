package com.example.zoostore.api.operations.tag.findbyid;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindTagByIdRequest implements OperationInput {
    private UUID id;
}
