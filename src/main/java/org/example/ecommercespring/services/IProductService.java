package org.example.ecommercespring.services;

import org.example.ecommercespring.dto.CategoryDTO;
import org.example.ecommercespring.dto.ProductDTO;
import org.example.ecommercespring.dto.ProductWithCategoryDTO;
import org.example.ecommercespring.exception.ProductNotFoundException;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    ProductDTO getProduct(int id) throws ProductNotFoundException, IOException;

    List<ProductDTO> getAllProductsByCategory(String type) throws Exception;

    ProductDTO createProduct(ProductDTO dto) throws Exception;
    ProductWithCategoryDTO getProductWithCategory(Long id) throws  Exception;
    List<ProductDTO> findByBrandAndPrice(int price,String brand) throws Exception;
    List<ProductDTO> findBykeyword(String keyword) throws Exception;
}
