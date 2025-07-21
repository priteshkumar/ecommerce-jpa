package org.example.ecommercespring.mappers;

import org.example.ecommercespring.dto.AllProductsOfCategoryDTO;
import org.example.ecommercespring.dto.CategoryDTO;
import org.example.ecommercespring.dto.ProductDTO;
import org.example.ecommercespring.entity.Category;
import org.example.ecommercespring.entity.Product;

import java.util.List;

public class CategoryMapper {
  public static CategoryDTO toDto(Category entity) {
    return CategoryDTO.builder().id(entity.getId()).name(entity.getName()).build();
  }

  public static Category toEntity(CategoryDTO dto) {
    return Category.builder().name(dto.getName()).build();
  }

  public static AllProductsOfCategoryDTO allProductsOfCategoryDTO(Category category) {
    List<ProductDTO> productDTOList =
        category.getProducts().stream().map(product -> ProductMapper.toDto(product)).toList();
    return AllProductsOfCategoryDTO.builder()
        .categoryId(category.getId())
        .name(category.getName())
        .productDTOList(productDTOList)
        .build();
  }
}
