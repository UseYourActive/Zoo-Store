package com.example.zoostore.core.processors.item;

import com.example.zoostore.api.operations.item.find.byids.*;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FindItemsByIdsOperationProcessor implements FindItemsByIdsOperation {
    private final ItemRepository itemRepository;

    @Override
    public FindItemsByIdsResponse process(FindItemsByIdsRequest input) {
        List<UUID> uuids = input
                .getIds()
                .stream()
                .map(UUID::fromString)
                .toList();

        List<Item> items = this.itemRepository.findAllByIdIn(uuids);

        List<FindItemsByIdsData> mappedItems = items
                .stream()
                .map(i -> FindItemsByIdsData.builder()
                        .id(i.getId().toString())
                        .title(i.getProductName())
                        .vendor(VendorResponse.builder()
                                        .id(i.getVendor().getId().toString())
                                        .name(i.getVendor().getName())
                                        .phoneNumber(i.getVendor().getPhone())
                                        .build())
                        .build())
                .toList();

        return FindItemsByIdsResponse
                .builder()
                .items(mappedItems)
                .build();
    }
}
