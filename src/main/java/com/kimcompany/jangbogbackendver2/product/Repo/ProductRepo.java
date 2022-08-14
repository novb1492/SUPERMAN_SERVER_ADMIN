package com.kimcompany.jangbogbackendver2.product.Repo;

import com.kimcompany.jangbogbackendver2.product.Model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductEntity,Long> {
}
