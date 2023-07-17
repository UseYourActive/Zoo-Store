package com.example.zoostore.api.operations.item.create;

import com.example.zoostore.api.base.OperationInput;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewItemRequest implements OperationInput {
    private String title;
    private String description;
    private UUID vendor;
    private Set<UUID> tags;
}
//@NotBlank // da ne e prazno