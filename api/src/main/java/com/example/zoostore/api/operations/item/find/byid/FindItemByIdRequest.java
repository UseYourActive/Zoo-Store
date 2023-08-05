package com.example.zoostore.api.operations.item.find.byid;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindItemByIdRequest implements OperationInput {
    @NotNull(message = "Item id is required!")
    private UUID id;
}
