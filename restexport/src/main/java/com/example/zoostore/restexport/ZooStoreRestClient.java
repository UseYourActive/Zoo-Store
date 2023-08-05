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
import com.example.zoostore.api.operations.item.unarchive.UnArchiveItemRequest;
import com.example.zoostore.api.operations.item.unarchive.UnArchiveItemResponse;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaRequest;
import com.example.zoostore.api.operations.multimedia.create.CreateNewMultimediaResponse;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLRequest;
import com.example.zoostore.api.operations.multimedia.edit.url.EditMultimediaURLResponse;
import com.example.zoostore.api.operations.multimedia.findall.FindAllMultimediaResponse;
import com.example.zoostore.api.operations.multimedia.findbyid.FindMultimediaByIdResponse;
import com.example.zoostore.api.operations.tag.create.CreateNewTagRequest;
import com.example.zoostore.api.operations.tag.create.CreateNewTagResponse;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameRequest;
import com.example.zoostore.api.operations.tag.edit.tag.EditTagNameResponse;
import com.example.zoostore.api.operations.tag.findall.FindAllTagsResponse;
import com.example.zoostore.api.operations.tag.findbyid.FindTagByIdResponse;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorRequest;
import com.example.zoostore.api.operations.vendor.create.CreateNewVendorResponse;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameRequest;
import com.example.zoostore.api.operations.vendor.edit.name.EditVendorNameResponse;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneRequest;
import com.example.zoostore.api.operations.vendor.edit.phone.EditVendorPhoneResponse;
import com.example.zoostore.api.operations.vendor.findall.FindAllVendorsResponse;
import com.example.zoostore.api.operations.vendor.findbyid.FindVendorByIdResponse;
import com.example.zoostore.api.operations.vendor.full.EditVendorRequest;
import com.example.zoostore.api.operations.vendor.full.EditVendorResponse;
import com.example.zoostore.api.operations.vendor.items.EditVendorItemsRequest;
import com.example.zoostore.api.operations.vendor.items.EditVendorItemsResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({"Content-Type: application/json"})
public interface ZooStoreRestClient {
    //region Items
    @RequestLine("GET /items/{request}")
    FindItemByIdResponse getItemById(@Param("request") String request);

    @RequestLine("GET /items/?includeArchived={includeArchived}&pageNumber={pageNumber}&numberOfItemsPerPage={numberOfItemsPerPage}&tagId={tagId}")
    FindAllItemsResponse getAllItems(@Param(value = "includeArchived") Boolean includeArchived,
                                     @Param(value = "pageNumber") Integer pageNumber,
                                     @Param(value = "numberOfItemsPerPage") Integer numberOfItemsPerPage,
                                     @Param(value = "tagId") String tagId);

    @RequestLine("GET /items/all/{request}")
    FindItemsByProductNameResponse findItemsByProductName(@Param String request);

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
    //endregion

    //region Multimedia
    @RequestLine("GET /multimedia/{multimediaId}")
    FindMultimediaByIdResponse findMultimediaById(@Param("multimediaId") String multimediaId);

    @RequestLine("GET /multimedia")
    FindAllMultimediaResponse findAllMultimedia();

    @RequestLine("POST /multimedia/create")
    CreateNewMultimediaResponse createMultimedia(@Param CreateNewMultimediaRequest request);

    @RequestLine("PATCH /multimedia/url")
    EditMultimediaURLResponse editMultimediaURl(@Param EditMultimediaURLRequest request);
    //endregion

    //region Tags
    @RequestLine("GET /tags/{tagId}")
    FindTagByIdResponse findTagById(@Param("tagId") String tagId);

    @RequestLine("GET /tags")
    FindAllTagsResponse findAllTags();

    @RequestLine("POST /tags/create")
    CreateNewTagResponse createNewTag(@Param CreateNewTagRequest request);

    @RequestLine("PATCH /tags/name")
    EditTagNameResponse editTagName(@Param EditTagNameRequest request);
    //endregion

    //region Vendors
    @RequestLine("GET /vendors")
    FindAllVendorsResponse findAllVendors();

    @RequestLine("GET /vendors/{vendorId}")
    FindVendorByIdResponse findVendorById(@Param("vendorId") String vendorId);

    @RequestLine("POST /vendors/create")
    CreateNewVendorResponse createVendor(@Param CreateNewVendorRequest request);

    @RequestLine("PATCH /vendors/full")
    EditVendorResponse editVendor(@Param EditVendorRequest request);

    @RequestLine("PATCH /vendors/name")
    EditVendorNameResponse editVendorName(@Param EditVendorNameRequest request);

    @RequestLine("PATCH /vendors/phone")
    EditVendorPhoneResponse editVendorPhone(@Param EditVendorPhoneRequest request);

    @RequestLine("PATCH /vendors/items")
    EditVendorItemsResponse editVendorItems(@Param EditVendorItemsRequest request);
    //endregion
}
