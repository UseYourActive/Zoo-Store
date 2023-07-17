package com.example.zoostore.api.operations.item.edit.title;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemProductNameResponse {
    private UUID id;
    private String title;
}
