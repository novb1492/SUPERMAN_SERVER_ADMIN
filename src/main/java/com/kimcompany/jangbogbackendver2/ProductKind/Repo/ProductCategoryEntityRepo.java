package com.kimcompany.jangbogbackendver2.ProductKind.Repo;

import com.kimcompany.jangbogbackendver2.ProductKind.Model.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryEntityRepo extends JpaRepository<ProductCategoryEntity,Long> {
}
