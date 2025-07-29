package org.example.ecommercespring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.MediaType;
import org.example.ecommercespring.dto.AllProductsOfCategoryDTO;
import org.example.ecommercespring.dto.CategoryDTO;
import org.example.ecommercespring.dto.ProductDTO;
import org.example.ecommercespring.exception.GlobalExceptionHandler;
import org.example.ecommercespring.services.ICategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {
  @Mock private ICategoryService categoryService;

  @InjectMocks private CategoryController categoryController;

  private MockMvc mockMvc;
  private static ObjectMapper mapper = new ObjectMapper();

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(categoryController)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
  }

  @Test
  @DisplayName("GET /api/categories should return all categories")
  void getAllCategories_shouldReturnAllCategories() throws Exception {
    // Arrange
    List<CategoryDTO> categories = new ArrayList<>();
    categories.add(CategoryDTO.builder().name("Electronics").id(1L).build());
    categories.add(CategoryDTO.builder().name("Books").id(2L).build());
    categories.add(CategoryDTO.builder().name("Clothing").id(3L).build());

    when(categoryService.getAllCategories()).thenReturn(categories);

    // Act
    mockMvc
        .perform(get("/api/categories").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].name").value("Electronics"))
        .andExpect(jsonPath("$[1].id").value(2))
        .andExpect(jsonPath("$[1].name").value("Books"))
        .andExpect(jsonPath("$[2].id").value(3))
        .andExpect(jsonPath("$[2].name").value("Clothing"));

    // Assert
    verify(categoryService, times(1)).getAllCategories();
  }

  @Test
  @DisplayName("POST /api/categories should create category")
  void createCategory_shouldCreateCategory() throws Exception {
    CategoryDTO categoryDTO = CategoryDTO.builder().name("Electronics").build();
    when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(categoryDTO);
    // String content = "{\"name\":\"Electronics\"}";
    String content = mapper.writeValueAsString(categoryDTO);
    mockMvc
        .perform(post("/api/categories").contentType(MediaType.APPLICATION_JSON).content(content))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Electronics"));
  }

  @Test
  @DisplayName("GET /api/categories/id/products")
  void getAllProductsOfCategory_shouldReturnAllProductsInCategory() throws Exception {
    List<ProductDTO> productDTOS = new ArrayList<>();
    ProductDTO productDTO =
        ProductDTO.builder().title("anton checkhov short stories").brand("penguinbooks").build();
    productDTOS.add(productDTO);
    AllProductsOfCategoryDTO productsOfCategoryDTO =
        AllProductsOfCategoryDTO.builder()
            .categoryId(2l)
            .name("Books")
            .productDTOList(productDTOS)
            .build();
    when(categoryService.getAllProductsOfCategory(anyLong())).thenReturn(productsOfCategoryDTO);
    Long categoryId = 2l;
    mockMvc
        .perform(
            get("/api/categories/{id}/products", categoryId)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Books"))
        .andExpect(jsonPath("$.categoryId").value(2l))
        .andExpect(jsonPath("$.productDTOList").isArray())
        .andExpect(jsonPath("$.productDTOList", hasSize(1)))
        .andExpect(
            jsonPath("$.productDTOList[0].title").value("anton " + "checkhov short stories"));
  }
}
