package com.example.zoostore.restexport;

import com.example.zoostore.api.operations.item.archive.ArchiveItemRequest;
import com.example.zoostore.api.operations.item.archive.ArchiveItemResponse;
import com.example.zoostore.api.operations.item.create.CreateNewItemRequest;
import com.example.zoostore.api.operations.item.create.CreateNewItemResponse;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionRequest;
import com.example.zoostore.api.operations.item.edit.description.EditItemDescriptionResponse;
import com.example.zoostore.api.operations.item.edit.full.EditItemRequest;
import com.example.zoostore.api.operations.item.edit.full.EditItemResponse;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLRequest;
import com.example.zoostore.api.operations.item.edit.multimedia.EditItemMultimediaURLResponse;
import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameRequest;
import com.example.zoostore.api.operations.item.edit.product.name.EditItemProductNameResponse;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagRequest;
import com.example.zoostore.api.operations.item.edit.tag.EditItemTagResponse;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorRequest;
import com.example.zoostore.api.operations.item.edit.vendor.EditItemVendorResponse;
import com.example.zoostore.api.operations.item.find.all.FindAllItemsResponse;
import com.example.zoostore.api.operations.item.find.byid.FindItemByIdResponse;
import com.example.zoostore.api.operations.item.find.byproductname.FindItemsByProductNameResponse;
import com.example.zoostore.api.operations.item.find.bytag.FindItemsByTagResponse;
import com.example.zoostore.api.operations.item.unarchive.UnArchiveItemRequest;
import com.example.zoostore.api.operations.item.unarchive.UnArchiveItemResponse;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaResponse;
import com.example.zoostore.api.operations.multimedia.edit.full.EditMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.edit.full.EditMultimediaResponse;
import com.example.zoostore.api.operations.multimedia.edit.item.EditMultimediaItemRequest;
import com.example.zoostore.api.operations.multimedia.edit.item.EditMultimediaItemResponse;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLRequest;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLResponse;
import com.example.zoostore.api.operations.multimedia.find.all.FindAllMultimediaResponse;
import com.example.zoostore.api.operations.multimedia.find.byid.FindMultimediaByIdResponse;
import com.example.zoostore.api.operations.tag.create.CreateNewTagRequest;
import com.example.zoostore.api.operations.tag.create.CreateNewTagResponse;
import com.example.zoostore.api.operations.tag.edit.title.EditTagTitleRequest;
import com.example.zoostore.api.operations.tag.edit.title.EditTagTitleResponse;
import com.example.zoostore.api.operations.tag.find.all.FindAllTagsResponse;
import com.example.zoostore.api.operations.tag.find.byid.FindTagByIdResponse;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorRequest;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorResponse;
import com.example.zoostore.api.operations.vendor.edit.full.EditVendorRequest;
import com.example.zoostore.api.operations.vendor.edit.full.EditVendorResponse;
import com.example.zoostore.api.operations.vendor.edit.items.EditVendorItemsRequest;
import com.example.zoostore.api.operations.vendor.edit.items.EditVendorItemsResponse;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameRequest;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameResponse;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneRequest;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneResponse;
import com.example.zoostore.api.operations.vendor.find.all.FindAllVendorsResponse;
import com.example.zoostore.api.operations.vendor.find.byid.FindVendorByIdResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({
    "Content-Type: application/json"
})
public interface ZooStoreRestExport {

    @RequestLine("GET /")
    FindAllVendorsResponse findAllVendors();

    @RequestLine("GET /{vendorId}")
    FindVendorByIdResponse findVendorById(@Param("vendorId") String vendorId);

    @RequestLine("POST /create")
    CreateNewVendorResponse createVendor(@Param CreateNewVendorRequest request);

    @RequestLine("PATCH /full")
    EditVendorResponse editVendor(@Param EditVendorRequest request);

    @RequestLine("PATCH /name")
    EditVendorNameResponse editVendorName(@Param EditVendorNameRequest request);

    @RequestLine("PATCH /phone")
    EditVendorPhoneResponse editVendorPhone(@Param EditVendorPhoneRequest request);

    @RequestLine("PATCH /items")
    EditVendorItemsResponse editVendorItems(@Param EditVendorItemsRequest request);

    @RequestLine("GET /")
    FindAllMultimediaResponse findAllMultimedia();

    @RequestLine("GET /{multimediaId}")
    FindMultimediaByIdResponse findMultimediaById(@Param("multimediaId") String multimediaId);

    @RequestLine("POST /create")
    CreateNewMultimediaResponse createMultimedia(@Param CreateNewMultimediaRequest request);

    @RequestLine("PATCH /full")
    EditMultimediaResponse editMultimedia(@Param EditMultimediaRequest request);

    @RequestLine("PATCH /item")
    EditMultimediaItemResponse editMultimediaItem(@Param EditMultimediaItemRequest request);

    @RequestLine("PATCH /url")
    EditMultimediaURLResponse editMultimediaURl(@Param EditMultimediaURLRequest request);

    @RequestLine("GET /items/{request}")
    FindItemByIdResponse getItemById(@Param("request") String request);

    @RequestLine("GET /items?includeArchived={includeArchived}&pageNumber={pageNumber}&numberOfItemsPerPage={numberOfItemsPerPage}&tagId={tagId}")
    FindAllItemsResponse getAllItems(@Param("includeArchived") Boolean includeArchived,
        @Param("pageNumber") Integer pageNumber,
        @Param("numberOfItemsPerPage") Integer numberOfItemsPerPage,
        @Param("tagId") String tagId);

    @RequestLine("GET /items/by-product-name/{request}?pageNumber={pageNumber}&numberOfItemsPerPage={numberOfItemsPerPage}")
    FindItemsByProductNameResponse findItemsByProductName(@Param("request") String request, @Param("pageNumber") Integer pageNumber, @Param("numberOfItemsPerPage") Integer numberOfItemsPerPage);

    @RequestLine("GET /items/find-by-tag?pageNumber={pageNumber}&numberOfItemsPerPage={numberOfItemsPerPage}&tagId={tagId}")
    FindItemsByTagResponse getItemByTagId(@Param("pageNumber") Integer pageNumber, @Param("numberOfItemsPerPage") Integer numberOfItemsPerPage, @Param("tagId") String tagId);

    @RequestLine("POST /items/create")
    CreateNewItemResponse createItem(@Param CreateNewItemRequest request);

    @RequestLine("PATCH /items/full")
    EditItemResponse editItem(@Param EditItemRequest request);

    @RequestLine("PATCH /items/product-name")
    EditItemProductNameResponse editItemProductName(@Param EditItemProductNameRequest request);

    @RequestLine("PATCH /items/description")
    EditItemDescriptionResponse editItemDescription(@Param EditItemDescriptionRequest request);

    @RequestLine("PATCH /items/vendor")
    EditItemVendorResponse editItemVendor(@Param EditItemVendorRequest request);

    @RequestLine("PATCH /items/multimedia")
    EditItemMultimediaURLResponse editItemMultimediaURL(@Param EditItemMultimediaURLRequest request);

    @RequestLine("PATCH /items/tag")
    EditItemTagResponse editItemTag(@Param EditItemTagRequest request);

    @RequestLine("PATCH /items/archive")
    ArchiveItemResponse archiveItem(@Param ArchiveItemRequest request);

    @RequestLine("PATCH /items/un-archive")
    UnArchiveItemResponse unArchiveItem(@Param UnArchiveItemRequest request);

    @RequestLine("GET /")
    FindAllTagsResponse findAllTags();

    @RequestLine("GET /{tagId}")
    FindTagByIdResponse findTagById(@Param("tagId") String tagId);

    @RequestLine("POST /create")
    CreateNewTagResponse createNewTag(@Param CreateNewTagRequest request);

    @RequestLine("PATCH /name")
    EditTagTitleResponse editTagName(@Param EditTagTitleRequest request);
}
