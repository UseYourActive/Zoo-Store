package com.example.zoostore.core.processors.vendor.findall;

import com.example.zoostore.api.operations.vendor.findall.FindAllVendorsInRepo;
import com.example.zoostore.api.operations.vendor.findall.FindAllVendorsOperation;
import com.example.zoostore.api.operations.vendor.findall.FindAllVendorsRequest;
import com.example.zoostore.api.operations.vendor.findall.FindAllVendorsResponse;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindAllVendorsOperationProcessor implements FindAllVendorsOperation {
    private final VendorRepository vendorRepository;

    @Override
    public FindAllVendorsResponse process(FindAllVendorsRequest findAllVendorsRequest) {
        List<Vendor> vendorsFoundInRepo = vendorRepository.findAll();

        return FindAllVendorsResponse.builder()
                .vendors(vendorsFoundInRepo.stream()
                        .map(this::mapAllFoundVendors)
                        .collect(Collectors.toSet()))
                .build();
    }

    private FindAllVendorsInRepo mapAllFoundVendors(Vendor vendor){
        return FindAllVendorsInRepo.builder()
                .id(vendor.getId())
                .name(vendor.getName())
                .phone(vendor.getPhone())
                .itemIds(vendor.getItems().stream()
                        .map(Item::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
