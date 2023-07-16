package com.example.zoostore.api.operations.item.edit.tag;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemTagResponse {
    private UUID id;
    private String title;
}
