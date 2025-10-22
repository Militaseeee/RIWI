package com.basicCrud.basicCrud.repository;

import com.basicCrud.basicCrud.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
