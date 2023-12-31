package com.example.zoostore.core.processors.tag;

import com.example.zoostore.api.operations.tag.find.all.FindAllTagsInRepo;
import com.example.zoostore.api.operations.tag.find.all.FindAllTagsOperation;
import com.example.zoostore.api.operations.tag.find.all.FindAllTagsRequest;
import com.example.zoostore.api.operations.tag.find.all.FindAllTagsResponse;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindAllTagsOperationProcessor implements FindAllTagsOperation {
    private final TagRepository tagRepository;

    @Override
    public FindAllTagsResponse process(final FindAllTagsRequest findAllTagsRequest) {
        log.info("Starting find all tags operation");

        List<Tag> tagsFoundInRepo = tagRepository.findAll();
        log.info("Found {} tags in the repository", tagsFoundInRepo.size());

        return FindAllTagsResponse.builder()
                .tags(tagsFoundInRepo.stream()
                        .map(this::mapAllTags)
                        .toList())
                .build();
    }

    private FindAllTagsInRepo mapAllTags(Tag tag){
        List<String> itemIds = tag.getItems().stream()
                .map(i -> String.valueOf(i.getId()))
                .toList();

        return FindAllTagsInRepo.builder()
                .id(String.valueOf(tag.getId()))
                .title(tag.getTitle())
                .itemIds(itemIds)
                .build();
    }
}
