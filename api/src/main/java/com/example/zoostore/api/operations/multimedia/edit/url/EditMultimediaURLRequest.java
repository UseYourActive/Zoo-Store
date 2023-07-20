package com.example.zoostore.api.operations.multimedia.edit.url;

import com.example.zoostore.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditMultimediaURLRequest implements OperationInput {
    @NotBlank(message = "Multimedia UUID is required!")
    private UUID multimediaId;

    @NotBlank(message = "URL is required!")
    private String url;
}
