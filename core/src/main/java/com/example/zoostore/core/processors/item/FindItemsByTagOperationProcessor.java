package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.find.bytag.FindItemsByTagInRepo;
import com.example.zoostore.api.operations.item.find.bytag.FindItemsByTagOperation;
import com.example.zoostore.api.operations.item.find.bytag.FindItemsByTagRequest;
import com.example.zoostore.api.operations.item.find.bytag.FindItemsByTagResponse;
import com.example.zoostore.core.exceptions.tag.TagNotFoundInRepositoryException;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Multimedia;
import com.example.zoostore.persistence.entities.Tag;
import com.example.zoostore.persistence.repositories.ItemRepository;
import com.example.zoostore.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindItemsByTagOperationProcessor implements FindItemsByTagOperation {
    private final TagRepository tagRepository;
    private final ItemRepository itemRepository;

    @Override
    public FindItemsByTagResponse process(FindItemsByTagRequest findItemByTagRequest) {
        Tag tag = this.tagRepository.findById(findItemByTagRequest.getTagId())
                .orElseThrow(TagNotFoundInRepositoryException::new);

        PageRequest pageable = PageRequest.of(findItemByTagRequest.getPageNumber(), findItemByTagRequest.getNumberOfItemsPerPage());
        Page<Item> items = this.itemRepository.findItemsByTagsContainingAndArchived(tag, false, pageable);

        return FindItemsByTagResponse.builder()
                .items(items.stream()
                        .map(this::mapToObject)
                        .toList())
                .build();
    }

    private FindItemsByTagInRepo mapToObject(Item item){
        return FindItemsByTagInRepo.builder()
                .itemId(item.getId())
                .isArchived(item.getArchived())
                .productName(item.getProductName())
                .vendorId(item.getVendor().getId())
                .description(item.getDescription())
                .tagIds(item.getTags().stream()
                        .map(Tag::getId)
                        .toList())
                .multimediaIds(item.getMultimedia().stream()
                        .map(Multimedia::getId)
                        .toList())
                .build();
    }
}
