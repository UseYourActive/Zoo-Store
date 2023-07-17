package com.example.zoostore.core.services.item;

import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetMultimediaService {
    private final MultimediaRepository multimediaRepository;

    public Set<Multimedia> getTagsByID(Set<UUID> request){
        return this.multimediaRepository.findAllByIdIn(request);
    }
}
