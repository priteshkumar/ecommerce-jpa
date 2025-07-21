package org.example.ecommercespring.services;

import org.example.ecommercespring.dto.ProductDTO;
import org.example.ecommercespring.dto.ProductWithCategoryDTO;
import org.example.ecommercespring.exception.ProductNotFoundException;
import org.example.ecommercespring.gateway.ICategoryGateway;
import org.example.ecommercespring.gateway.IProductGateway;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements IProductService {
  private final IProductGateway productGateway;

  public FakeStoreProductService(IProductGateway productGateway) {
    this.productGateway = productGateway;
  }

  @Override
  public ProductDTO getProduct(int id) throws ProductNotFoundException, IOException {
    return this.productGateway.getProduct(id);
  }

  @Override
  public List<ProductDTO> getAllProductsByCategory(String type) throws IOException {
    return this.productGateway.getAllProductsByCategory(type);
  }

  public ProductDTO createProduct(ProductDTO dto) {
    return null;
  }

  @Override
  public ProductWithCategoryDTO getProductWithCategory(Long id) throws Exception {
    return null;
  }

  @Override
  public List<ProductDTO> findByBrandAndPrice(int price, String brand) throws Exception {
    return List.of();
  }

  @Override
  public List<ProductDTO> findBykeyword(String keyword) throws Exception {
    return List.of();
  }
}
