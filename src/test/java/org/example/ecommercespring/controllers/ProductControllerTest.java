package org.example.ecommercespring.controllers;

import jakarta.ws.rs.core.MediaType;
import org.example.ecommercespring.dto.ProductDTO;
import org.example.ecommercespring.services.IProductService;
import org.example.ecommercespring.services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/* this doesnt work
debug later
 */
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockitoBean private IProductService productService;

  @Test
  @DisplayName("GET /api/products/{id} should return single product")
  void getSingleProduct_shouldReturnProduct() throws Exception {
    ProductDTO productDTO =
        ProductDTO.builder().title("anton chechov stories").brand("penguinbooks").build();
    when(productService.getProduct(anyInt())).thenReturn(productDTO);
    Integer productId = 1;
    mockMvc
        .perform(get("/api/products/{id}", productId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("anton chechov stories"))
        .andExpect(jsonPath("$.brand").value("penguinbooks"));
  }
}
