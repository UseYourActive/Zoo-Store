package com.example.zoostore.api.operations.item.edit.full;

import com.example.zoostore.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class EditItemRequest implements OperationInput {
    @org.hibernate.validator.constraints.UUID
    @JsonIgnore
    private final String id;

    @Nullable
    private final String productName;

    @Nullable
    private final String description;

    @Nullable
    private final String vendorId;

    @Nullable
    private final List<String> multimedia;

    @Nullable
    private final List<String> tags;

    @Nullable
    private final Boolean isArchived;
}
