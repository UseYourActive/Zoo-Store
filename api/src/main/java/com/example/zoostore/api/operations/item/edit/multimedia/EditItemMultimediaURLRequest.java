package com.example.zoostore.api.operations.item.edit.multimedia;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemMultimediaURLRequest implements OperationInput {
    @NotNull(message = "Item id is required!")
    private String itemId;

    @org.hibernate.validator.constraints.UUID
    private List<String> multimediaIds;
}
