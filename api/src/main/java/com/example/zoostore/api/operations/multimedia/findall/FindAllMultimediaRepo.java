package com.example.zoostore.api.operations.multimedia.findall;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllMultimediaRepo {
    private UUID id;
    private String url;
    private UUID itemId;
}
