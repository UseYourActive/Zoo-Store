package com.example.zoostore.api.operations.tag.edit.tag;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditTagNameRequest {
    private UUID id;
    private String title;
}
