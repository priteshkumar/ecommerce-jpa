package org.example.ecommercespring.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
  private Long id;
  private String title;
  private String image;
  private int price;
  private String description;
  private String brand;
  private String model;
  private String color;
  private Long categoryId;
  private boolean popular;
  private int discount;

  @Override
  public String toString() {
    return "ProductDTO{"
        + "id="
        + id
        + ", title='"
        + title
        + '\''
        + ", image='"
        + image
        + '\''
        + ", price='"
        + price
        + '\''
        + ", description='"
        + description
        + '\''
        + ", brand='"
        + brand
        + '\''
        + ", model='"
        + model
        + '\''
        + ", color='"
        + color
        + '\''
        + ", category='"
        + categoryId
        + '\''
        + ", popular="
        + popular
        + ", discount='"
        + discount
        + '\''
        + '}';
  }
}
