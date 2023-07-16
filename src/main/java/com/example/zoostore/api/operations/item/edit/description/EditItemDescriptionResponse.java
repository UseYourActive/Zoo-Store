package com.example.zoostore.api.operations.item.edit.description;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemDescriptionResponse {
    private UUID id;
    private String description;
}
