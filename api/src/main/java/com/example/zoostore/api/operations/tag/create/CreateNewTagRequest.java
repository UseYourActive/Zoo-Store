package com.example.zoostore.api.operations.tag.create;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewTagRequest implements OperationInput {
    @NotBlank(message = "Was not a valid input!")
    private String title;
}
