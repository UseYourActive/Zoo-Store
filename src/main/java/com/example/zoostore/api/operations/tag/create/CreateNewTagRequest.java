package com.example.zoostore.api.operations.tag.create;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateNewTagRequest {
    private String title;
}
