package com.example.zoostore.api.operations.multimedia.edit;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditMultimediaURLRequest {
    private UUID id;
    private String url;
}
