package org.example.ecommercespring.services;

import org.example.ecommercespring.dto.ProductDTO;
import org.example.ecommercespring.dto.ProductWithCategoryDTO;
import org.example.ecommercespring.entity.Category;
import org.example.ecommercespring.entity.Product;
import org.example.ecommercespring.exception.ProductNotFoundException;
import org.example.ecommercespring.mappers.ProductMapper;
import org.example.ecommercespring.repository.CategoryRepository;
import org.example.ecommercespring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("productService")
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDTO getProduct(int id) throws ProductNotFoundException {
        Product product =
                productRepository.findById((long) id).orElseThrow(() -> new ProductNotFoundException(
                        "Product not found"));
        return ProductMapper.toDto(product);
    }

    @Override
    public List<ProductDTO> getAllProductsByCategory(String type) throws Exception {
        return List.of();
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto) throws Exception {
        Category category =
                categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new Exception("Category not found"));
        Product product = productRepository.save(ProductMapper.toEntity(dto,
                category));
        return ProductMapper.toDto(product);
    }

    @Override
    public ProductWithCategoryDTO getProductWithCategory(Long id) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found"));
        return ProductMapper.toProductWithCategoryDTO(product);
    }

    @Override
    public List<ProductDTO> findByBrandAndPrice(int price, String brand) throws Exception {
        List<Product> products = productRepository.findByBrandAndPrice(price,
                brand);
        return products.stream().map(product -> ProductMapper.toDto(product)).toList();
    }

    @Override
    public List<ProductDTO> findBykeyword(String keyword) throws Exception {
        List<Product> products = productRepository.searchFullText(keyword);
        return products.stream().map(product -> ProductMapper.toDto(product)).toList();
    }
}
