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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemRequest implements OperationInput {
    @org.hibernate.validator.constraints.UUID
    @JsonIgnore
    private String id;

    @Nullable
    private String productName;

    @Nullable
    private String description;

    @Nullable
    private String vendorId;

    @Nullable
    private List<String> multimedia;

    @Nullable
    private List<String> tags;

    @Nullable
    private Boolean isArchived;
}
