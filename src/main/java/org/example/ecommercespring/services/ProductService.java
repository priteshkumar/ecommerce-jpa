package org.example.ecommercespring.services;

import org.example.ecommercespring.dto.ProductDTO;
import org.example.ecommercespring.entity.Product;
import org.example.ecommercespring.mappers.ProductMapper;
import org.example.ecommercespring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("productService")
public class ProductService implements IProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public ProductDTO getProduct(int id) throws Exception {
        Product product = repo.findById((long) id).orElseThrow(() -> new Exception("Product not found"));
        return ProductMapper.toDto(product);
    }

    @Override
    public List<ProductDTO> getAllProductsByCategory(String type) throws Exception {
        return List.of();
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto) throws Exception {
        Product product = repo.save(ProductMapper.toEntity(dto));
        return ProductMapper.toDto(product);
    }
}
