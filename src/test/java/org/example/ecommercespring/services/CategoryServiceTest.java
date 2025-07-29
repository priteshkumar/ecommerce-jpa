package org.example.ecommercespring.services;

import org.example.ecommercespring.dto.AllProductsOfCategoryDTO;
import org.example.ecommercespring.dto.CategoryDTO;
import org.example.ecommercespring.entity.Category;
import org.example.ecommercespring.entity.Product;
import org.example.ecommercespring.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
  @Mock private CategoryRepository categoryRepository;

  @InjectMocks private CategoryService categoryService;

  private CategoryDTO categoryDTO;
  private Category category1;
  private Category category2;
  private Category category3;

  @BeforeEach
  void setUp() {
    categoryDTO = CategoryDTO.builder().name("Electronics").build();
    category1 = Category.builder().name("Electronics").build();
    category1.setId(1l);
    category2 = Category.builder().name("Books").build();
    category2.setId(2l);
    category3 = Category.builder().name("Clothing").build();
    category3.setId(3l);
  }

  @Test
  @DisplayName("should return all categories successfully")
  void getAllCategories_shouldReturnAllCategories() throws IOException {
    // Arrange
    List<Category> categories = new ArrayList<>();

    categories.add(category1);
    categories.add(category2);
    categories.add(category3);

    when(categoryRepository.findAll())
        .thenReturn(categories); // mocked the repository to return the categories

    // Act
    List<CategoryDTO> result =
        categoryService.getAllCategories(); // service is actually really called

    // Assert
    assertEquals(result.size(), 3);
    assertEquals(result.get(0).getName(), "Electronics");
    assertEquals(result.get(1).getName(), "Books");
    assertEquals(result.get(2).getName(), "Clothing");
    verify(categoryRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("should create category successfully")
  void createCategory_shouldCreateCategorySuccessfully() {
    when(categoryRepository.save(any(Category.class))).thenReturn(category1);
    CategoryDTO result = categoryService.createCategory(categoryDTO);
    assertEquals(result.getName(), "Electronics");
    verify(categoryRepository, times(1)).save(any(Category.class));
  }

  @Test
  @DisplayName("should return category by name")
  void getByName_shouldReturnCategoryByname() throws Exception {
    when(categoryRepository.findByName("Books")).thenReturn(Optional.of(category2));
    CategoryDTO result = categoryService.getByName("Books");
    assertEquals(result.getName(), "Books");
    assertEquals(result.getId(), 2);
    verify(categoryRepository, times(1)).findByName("Books");
  }

  @Test
  @DisplayName("should return all products in category")
  void getAllProductsOfCategory_shouldReturnAllProductsinCategory() throws Exception {
    List<Product> products = new ArrayList<>();
    Product product =
        Product.builder()
            .title("anton chekhov short stories")
            .brand("penguinbooks")
            .category(category2)
            .build();

    products.add(product);
    category2.setProducts(products);
    when(categoryRepository.findById(2l)).thenReturn(Optional.of(category2));
    AllProductsOfCategoryDTO result = categoryService.getAllProductsOfCategory(2l);
    assertEquals(result.getCategoryId(), 2l);
    assertEquals(result.getName(), "Books");
    assertEquals(result.getProductDTOList().size(), 1);
    assertEquals(result.getProductDTOList().get(0).getTitle(), "anton chekhov " + "short stories");
    assertEquals(result.getProductDTOList().get(0).getBrand(),"penguinbooks");

    verify(categoryRepository, times(1)).findById(2l);
  }
}
