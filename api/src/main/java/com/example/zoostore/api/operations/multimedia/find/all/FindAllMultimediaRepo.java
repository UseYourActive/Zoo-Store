package com.example.zoostore.api.operations.multimedia.find.all;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllMultimediaRepo {
    private String id;
    private String url;
    private String itemId;
}
