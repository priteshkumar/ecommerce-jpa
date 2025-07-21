package org.example.ecommercespring.controllers;

import org.example.ecommercespring.dto.AllProductsOfCategoryDTO;
import org.example.ecommercespring.dto.CategoryDTO;
import org.example.ecommercespring.dto.ProductDTO;
import org.example.ecommercespring.services.ICategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  private final ICategoryService categoryService;

  public CategoryController(@Qualifier("categoryService") ICategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> getAllCategories() throws IOException {
    List<CategoryDTO> categoryDTOS = this.categoryService.getAllCategories();
    return ResponseEntity.ok(categoryDTOS);
  }

  @PostMapping
  public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO dto) throws Exception {
    CategoryDTO categoryDTO = this.categoryService.createCategory(dto);
    return ResponseEntity.ok(categoryDTO);
  }

  @GetMapping("/{id}/products")
  public ResponseEntity<AllProductsOfCategoryDTO> getAllProductsOfCategory(@PathVariable Long id)
      throws Exception {

    AllProductsOfCategoryDTO dto = categoryService.getAllProductsOfCategory(id);
    return ResponseEntity.ok(dto);
  }
}
