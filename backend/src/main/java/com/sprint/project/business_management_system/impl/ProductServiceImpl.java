package com.sprint.project.business_management_system.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.Entity.Product;
import com.sprint.project.business_management_system.Entity.ProductLine;
import com.sprint.project.business_management_system.exception.ResourceNotFoundException;
import com.sprint.project.business_management_system.repository.ProductLineRepository;
import com.sprint.project.business_management_system.repository.ProductRepository;
import com.sprint.project.business_management_system.requestDto.ProductRequestDto;
import com.sprint.project.business_management_system.responseDto.ProductResponseDto;
import com.sprint.project.business_management_system.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private ProductLineRepository productLineRepo;

    private Product mapToEntity(ProductRequestDto dto) {
        Product p = new Product();
        p.setProductCode(dto.getProductCode());
        p.setProductName(dto.getProductName());
        p.setProductScale(dto.getProductScale());
        p.setProductVendor(dto.getProductVendor());
        p.setProductDesc(dto.getProductDesc());
        p.setQuantityInStock(dto.getQuantityInStock());
        p.setBuyPrice(dto.getBuyPrice());
        p.setMsrp(dto.getMsrp());

        ProductLine pl = productLineRepo.findById(dto.getProductLine())
                .orElseThrow(() -> new ResourceNotFoundException("Product line not found"));
        p.setProductLine(pl);

        return p;
    }

    private ProductResponseDto mapToDto(Product p) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setProductCode(p.getProductCode());
        dto.setProductName(p.getProductName());
        dto.setProductLine(p.getProductLine().getProductLine());
        dto.setProductScale(p.getProductScale());
        dto.setProductVendor(p.getProductVendor());
        dto.setProductDesc(p.getProductDesc());
        dto.setQuantityInStock(p.getQuantityInStock());
        dto.setBuyPrice(p.getBuyPrice());
        dto.setMsrp(p.getMsrp());
        return dto;
    }

    public List<ProductResponseDto> getAllProducts() {
        return repo.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ProductResponseDto getProductById(String id) {
        Product product = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return mapToDto(product);
    }

    public ProductResponseDto saveProduct(ProductRequestDto productDto) {
        Product product = mapToEntity(productDto);
        return mapToDto(repo.save(product));
    }

    public List<ProductResponseDto> getProductsByProductLine(String productLine) {
        return repo.findByProductLine_ProductLine(productLine).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public void deleteProduct(String id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with code: " + id);
        }
        repo.deleteById(id);
    }
}
