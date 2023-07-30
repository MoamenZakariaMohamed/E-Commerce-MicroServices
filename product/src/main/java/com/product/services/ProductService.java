package com.product.services;

import com.product.data.ProductDTO;
import com.product.entities.Product;
import com.product.exceptions.DataHasNotChangedException;
import com.product.exceptions.NotFoundException;
import com.product.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("this product not found" + id));
    }

    public List<Product> getProductsByIds(List<Long> id) {
        return productRepository.findAllById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDTO product) {
        Product existingProduct = getProductById(id);
        boolean changes = false;
        changes = Product.updateIfNotSame(changes, product, existingProduct);
        if (!changes) {
            throw new DataHasNotChangedException("لم يوجد اي تغيير في البيانات ");
        }
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


}
