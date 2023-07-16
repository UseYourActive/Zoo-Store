package com.example.zoostore.api.operations.tag.create;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewTagResponse {
    private UUID id;
    private String title;
}
