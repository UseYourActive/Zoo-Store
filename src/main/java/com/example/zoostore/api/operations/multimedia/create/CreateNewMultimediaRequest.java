package com.example.zoostore.api.operations.multimedia.create;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewMultimediaRequest {
    private UUID id;
    private String url;
}
