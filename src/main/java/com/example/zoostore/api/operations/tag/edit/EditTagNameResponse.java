package com.example.zoostore.api.operations.tag.edit;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditTagNameResponse {
    private UUID id;
    private String title;
}
