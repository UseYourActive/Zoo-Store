package com.example.zoostore.api.operations.multimedia.findall;

import com.example.zoostore.api.base.OperationResult;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllMultimediaResponse implements OperationResult {
    private Set<FindAllMultimediaRepo> multimedia;
}
