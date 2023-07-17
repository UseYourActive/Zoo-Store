package com.example.zoostore.core.processors.item;

import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetTagService {
    private final TagRepository tagRepository;

    public Set<Tag> getTagsByID(Set<UUID> request){
        return this.tagRepository.findAllByIdIn(request);
    }
}
