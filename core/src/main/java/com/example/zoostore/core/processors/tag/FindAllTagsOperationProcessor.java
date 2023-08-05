package com.example.zoostore.core.processors.tag;

import com.example.zoostore.api.operations.tag.find.all.FindAllTagsInRepo;
import com.example.zoostore.api.operations.tag.find.all.FindAllTagsOperation;
import com.example.zoostore.api.operations.tag.find.all.FindAllTagsRequest;
import com.example.zoostore.api.operations.tag.find.all.FindAllTagsResponse;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindAllTagsOperationProcessor implements FindAllTagsOperation {
    private final TagRepository tagRepository;

    @Override
    public FindAllTagsResponse process(FindAllTagsRequest findAllTagsRequest) {
        List<Tag> tagsFoundInRepo = tagRepository.findAll();

        return FindAllTagsResponse.builder()
                .tags(tagsFoundInRepo.stream()
                        .map(this::mapAllTags)
                        .collect(Collectors.toSet()))
                .build();
    }

    private FindAllTagsInRepo mapAllTags(Tag tag){
        return FindAllTagsInRepo.builder()
                .id(tag.getId())
                .title(tag.getTitle())
                .itemIds(tag.getItems().stream()
                        .map(Item::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
