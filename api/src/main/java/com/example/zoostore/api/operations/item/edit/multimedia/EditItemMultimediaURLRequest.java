package com.example.zoostore.api.operations.item.edit.multimedia;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemMultimediaURLRequest implements OperationInput {
    @NotNull(message = "Vendor UUID is required!")
    private UUID itemId;

    @NotEmpty(message = "URLs are required!")
    private Set<String> urls;
}
