package org.example.ecommercespring.controllers;

import org.example.ecommercespring.dto.ProductDTO;
import org.example.ecommercespring.entity.Product;
import org.example.ecommercespring.repository.ProductRepository;
import org.example.ecommercespring.services.ICategoryService;
import org.example.ecommercespring.services.IProductService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService productService;
    private final ProductRepository productRepository;

    public ProductController(IProductService productService,
                             ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/{id}")
    public ProductDTO getSingleProduct(@PathVariable int id) throws IOException {
        //return "ID: " + id;
        return this.productService.getProduct(id);
    }

    @GetMapping("/product/{id}")
    public Product getSingleProductById(@PathVariable long id) throws IOException {
        //return "ID: " + id;
        //return this.productService.getProduct(id);
        Optional<Product> product = this.productRepository.findById(id);
        System.out.println(product.get());
        return product.get();
    }

    @GetMapping("/product/category")
    public List<Product> getAllProductsByCategory(@RequestParam String type) throws IOException {
        return this.productRepository.findByCategory(type);
    }

    @GetMapping("/category")
    public List<ProductDTO> getProductsByCategory(@RequestParam String type) throws IOException {
        return this.productService.getAllProductsByCategory(type);
    }

    @PostMapping("/product")
    public Product newProduct(@RequestBody ProductDTO productDTO) {
        Product product =
                Product.builder().title(productDTO.getTitle())
                        .brand(productDTO.getBrand())
                        .color(productDTO.getColor())
                        .model(productDTO.getModel())
                        .price(Integer.parseInt(productDTO.getPrice()))
                        .category(productDTO.getCategory())
                        .description(productDTO.getDescription())
                        .discount(Integer.parseInt(productDTO.getDiscount()))
                        .popular(productDTO.isPopular())
                        .image(productDTO.getImage()).build();

        return productRepository.save(product);
    }

}
