package org.example.ecommercespring.services;


import org.example.ecommercespring.dto.AllProductsOfCategoryDTO;
import org.example.ecommercespring.dto.CategoryDTO;
import org.example.ecommercespring.gateway.ICategoryGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("fakeStoreCategoryService")
public class FakeStoreCategoryService implements ICategoryService {

    private final ICategoryGateway categoryGateway;

    public FakeStoreCategoryService(@Qualifier("fakeStoreRestTemplateGateway") ICategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        return this.categoryGateway.getAllCategories();
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public CategoryDTO getByName(String name) throws Exception {
        return null;
    }

    @Override
    public AllProductsOfCategoryDTO getAllProductsOfCategory(Long categoryId) throws Exception {
        return null;
    }
}
