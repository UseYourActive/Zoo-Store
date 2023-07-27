package com.example.zoostore.restexport;

import com.example.zoostore.api.operations.item.findall.FindAllItemsResult;
import com.example.zoostore.api.operations.item.findbyid.FindItemByIdResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({"Content-Type: application/json"})
public interface ZooStoreRestClient {
    @RequestLine("GET /items/{request}")
    FindItemByIdResponse getItemById(@Param String request);

    @RequestLine("GET /items/find-all?includeArchived={includeArchived}&pageNumber={pageNumber}&numberOfItemsPerPage={numberOfItemsPerPage}&tagId={tagId}")
    FindAllItemsResult getAllItems(@Param(value = "includeArchived") Boolean includeArchived,
                                   @Param(value = "pageNumber") Integer pageNumber,
                                   @Param(value = "numberOfItemsPerPage") Integer numberOfItemsPerPage,
                                   @Param(value = "tagId") @org.hibernate.validator.constraints.UUID String tagId);
}
