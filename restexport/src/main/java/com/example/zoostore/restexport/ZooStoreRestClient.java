package com.example.zoostore.restexport;

import com.example.zoostore.api.operations.item.findbyid.FindItemByIdResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Headers({"Content-Type: application/json"})
public interface ZooStoreRestClient {
    @RequestLine("GET /items/{request}")
    FindItemByIdResponse getItemById(@Param String request);
}
