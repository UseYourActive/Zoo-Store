package com.example.zoostore.restexport;

import com.example.zoostore.api.operations.item.findbyid.FindItemByIdResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.UUID;

@Headers("accept=application/json")
public interface ZooStoreRestClient {
    @RequestLine("GET /items/{itemId}")
    FindItemByIdResponse getItemById(@Param("itemId") UUID request);
}
