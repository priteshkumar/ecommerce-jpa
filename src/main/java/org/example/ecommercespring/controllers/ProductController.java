package org.example.ecommercespring.controllers;

import org.example.ecommercespring.dto.ProductDTO;
import org.example.ecommercespring.dto.ProductWithCategoryDTO;
import org.example.ecommercespring.entity.Product;
import org.example.ecommercespring.repository.ProductRepository;
import org.example.ecommercespring.services.ICategoryService;
import org.example.ecommercespring.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService productService;

    public ProductController(@Qualifier("productService") IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getSingleProduct(@PathVariable int id) throws Exception {
        //return "ID: " + id;
        ProductDTO productDTO = this.productService.getProduct(id);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/category")
    public List<ProductDTO> getProductsByCategory(@RequestParam String type) throws Exception {
        return this.productService.getAllProductsByCategory(type);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) throws Exception {
        ProductDTO productDTO = this.productService.createProduct(dto);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<ProductWithCategoryDTO> getProductWithCategory(@PathVariable long id) throws Exception {

        ProductWithCategoryDTO dto =  productService.getProductWithCategory(id);
        return ResponseEntity.ok(dto);
    }

}
