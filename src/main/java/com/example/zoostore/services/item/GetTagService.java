package com.example.zoostore.services.item;

import com.example.zoostore.data.entities.Tag;
import com.example.zoostore.data.repositories.TagRepository;
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
