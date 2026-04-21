package com.sprint.project.business_management_system.testService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.sprint.project.business_management_system.Entity.Product;
import com.sprint.project.business_management_system.Entity.ProductLine;
import com.sprint.project.business_management_system.impl.ProductServiceImpl;
import com.sprint.project.business_management_system.repository.ProductRepository;

class ProductTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repo;

    private Product product;
    private ProductLine productLine;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        // Sample ProductLine
        productLine = new ProductLine();
        productLine.setProductLine("Classic Cars");

        // Sample Product
        product = new Product();
        product.setProductCode("P100");
        product.setProductName("Car Model X");
        product.setProductLine(productLine);
        product.setProductScale("1:10");
        product.setProductVendor("Vendor A");
        product.setProductDesc("Sample Product");
        product.setQuantityInStock((short) 50);
        product.setBuyPrice(new BigDecimal("1000"));
        product.setMsrp(new BigDecimal("1500"));
    }

    // -------- 10 IMPORTANT TEST CASES --------

    // 1. Get all products - success
    @Test
    void testGetAllProducts() {
        when(repo.findAll()).thenReturn(List.of(product));
        assertEquals(1, service.getAllProducts().size());
    }

    // 2. Get all products - empty
    @Test
    void testGetAllProductsEmpty() {
        when(repo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(service.getAllProducts().isEmpty());
    }

    // 3. Get product by ID - found
    @Test
    void testGetProductById() {
        when(repo.findById("P100")).thenReturn(Optional.of(product));
        assertEquals("Car Model X", service.getProductById("P100").getProductName());
    }

    // 4. Get product by ID - not found
    @Test
    void testGetProductByIdNotFound() {
        when(repo.findById("P100")).thenReturn(Optional.empty());
        assertNull(service.getProductById("P100"));
    }

    // 5. Save product - success
    @Test
    void testSaveProduct() {
        when(repo.save(product)).thenReturn(product);
        assertNotNull(service.saveProduct(product));
    }

    // 6. Save product - verify repository call
    @Test
    void testSaveProductVerify() {
        when(repo.save(product)).thenReturn(product);
        service.saveProduct(product);
        verify(repo).save(product);
    }

    // 7. Get products by product line - success
    @Test
    void testGetProductsByProductLine() {
        when(repo.findByProductLine_ProductLine("Classic Cars"))
                .thenReturn(List.of(product));

        assertEquals(1, service.getProductsByProductLine("Classic Cars").size());
    }

    // 8. Get products by product line - empty
    @Test
    void testGetProductsByProductLineEmpty() {
        when(repo.findByProductLine_ProductLine("Trucks"))
                .thenReturn(new ArrayList<>());

        assertTrue(service.getProductsByProductLine("Trucks").isEmpty());
    }

    // 9. Verify repository call for product line
    @Test
    void testProductLineVerify() {
        when(repo.findByProductLine_ProductLine("Classic Cars"))
                .thenReturn(List.of(product));

        service.getProductsByProductLine("Classic Cars");
        verify(repo).findByProductLine_ProductLine("Classic Cars");
    }

    // 10. Validate product fields after save
    @Test
    void testProductDataIntegrity() {
        when(repo.save(product)).thenReturn(product);

        Product result = service.saveProduct(product);

        assertEquals("P100", result.getProductCode());
        assertEquals("Car Model X", result.getProductName());
        assertEquals(new BigDecimal("1000"), result.getBuyPrice());
        assertEquals(new BigDecimal("1500"), result.getMsrp());
    }
 // 11. Multiple products by productLine
    @Test
    void testMultipleProductsByLine() {
        when(repo.findByProductLine_ProductLine("Classic Cars"))
                .thenReturn(List.of(product, product));

        assertEquals(2,
            service.getProductsByProductLine("Classic Cars").size());
    }

    // 12. Verify findAll called
    @Test
    void testProductFindAllVerify() {
        when(repo.findAll()).thenReturn(List.of(product));

        service.getAllProducts();
        verify(repo).findAll();
    }

    // 13. Null productLine handling
    @Test
    void testNullProductLine() {
        when(repo.findByProductLine_ProductLine(null))
                .thenReturn(new ArrayList<>());

        assertTrue(service.getProductsByProductLine(null).isEmpty());
    }
}