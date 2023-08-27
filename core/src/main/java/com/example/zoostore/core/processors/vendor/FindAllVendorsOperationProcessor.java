package com.example.zoostore.core.processors.vendor;

import com.example.zoostore.api.operations.vendor.find.all.FindAllVendorsInRepo;
import com.example.zoostore.api.operations.vendor.find.all.FindAllVendorsOperation;
import com.example.zoostore.api.operations.vendor.find.all.FindAllVendorsRequest;
import com.example.zoostore.api.operations.vendor.find.all.FindAllVendorsResponse;
import com.example.zoostore.persistence.entities.Item;
import com.example.zoostore.persistence.entities.Vendor;
import com.example.zoostore.persistence.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindAllVendorsOperationProcessor implements FindAllVendorsOperation {
    private final VendorRepository vendorRepository;

    @Override
    public FindAllVendorsResponse process(final FindAllVendorsRequest findAllVendorsRequest) {
        log.info("Starting find all vendors operation.");

        List<Vendor> vendorsFoundInRepo = vendorRepository.findAll();
        log.info("Find all vendors operation completed. Found {} vendors.", vendorsFoundInRepo.size());

        return FindAllVendorsResponse.builder()
                .vendors(vendorsFoundInRepo.stream()
                        .map(this::mapAllFoundVendors)
                        .toList())
                .build();
    }

    private FindAllVendorsInRepo mapAllFoundVendors(Vendor vendor){
        List<String> itemIds = vendor.getItems().stream()
                .map(i -> String.valueOf(i.getId()))
                .toList();

        return FindAllVendorsInRepo.builder()
                .id(String.valueOf(vendor.getId()))
                .name(vendor.getName())
                .phone(vendor.getPhone())
                .itemIds(itemIds)
                .build();
    }
}
