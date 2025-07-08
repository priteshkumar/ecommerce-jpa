package org.example.ecommercespring.services;

import org.example.ecommercespring.dto.CategoryDTO;
import org.example.ecommercespring.entity.Category;
import org.example.ecommercespring.mappers.CategoryMapper;
import org.example.ecommercespring.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("categoryService")
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws IOException {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category ->
                        CategoryMapper.toDto(category))
                .toList();
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category =
                categoryRepository.save(CategoryMapper.toEntity(categoryDTO));
        return CategoryMapper.toDto(category);
    }
}
