package com.product.entities;

import com.product.data.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

    private String category;

    private Integer stockQuantity;

    @Column(name = "image_url")
    private String imageUrl;




    public static Product createProduct(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .category(productDTO.getCategory())
                .stockQuantity(productDTO.getStockQuantity())
                .imageUrl(productDTO.getImageUrl())
                .build();
    }


    public static boolean updateIfNotSame(
            boolean changes, ProductDTO updatedProductDTO, Product product) {
        boolean modified = changes;

        if (!updatedProductDTO.getName().equals(product.getName())) {
            product.setName(updatedProductDTO.getName());
            modified = true;
        }

        if (!updatedProductDTO.getDescription().equals(product.getDescription())) {
            product.setDescription(updatedProductDTO.getDescription());
            modified = true;
        }

        if (!(updatedProductDTO.getPrice() == product.getPrice())) {
            product.setPrice(updatedProductDTO.getPrice());
            modified = true;
        }

        if (!updatedProductDTO.getCategory().equals(product.getCategory())) {
            product.setCategory(updatedProductDTO.getCategory());
            modified = true;
        }


        if (!updatedProductDTO.getStockQuantity().equals(product.getStockQuantity())) {
            product.setStockQuantity(updatedProductDTO.getStockQuantity());
            modified = true;
        }

        if (!updatedProductDTO.getImageUrl().equals(product.getImageUrl())) {
            product.setImageUrl(updatedProductDTO.getImageUrl());
            modified = true;
        }


        return modified;
    }


}
