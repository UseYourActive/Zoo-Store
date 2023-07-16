package com.example.zoostore.api.operations.item.edit.multimedia;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EditItemMultimediaURLRequest {
    private UUID id;
    private Set<String> url;
}
