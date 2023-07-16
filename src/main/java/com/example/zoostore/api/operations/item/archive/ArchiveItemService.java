package com.example.zoostore.api.operations.item.archive;

import com.example.zoostore.exceptions.item.ItemNotFoundInRepositoryException;

public interface ArchiveItemService {
    ArchiveItemResponse archiveItem(ArchiveItemRequest request) throws ItemNotFoundInRepositoryException;
}
