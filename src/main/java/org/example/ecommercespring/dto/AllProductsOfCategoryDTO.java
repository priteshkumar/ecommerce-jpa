package org.example.ecommercespring.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllProductsOfCategoryDTO {
    private Long categoryId;
    private String name;
    private List<ProductDTO> productDTOList;
}
