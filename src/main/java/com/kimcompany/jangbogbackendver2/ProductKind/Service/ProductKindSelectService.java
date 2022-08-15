package com.kimcompany.jangbogbackendver2.ProductKind.Service;

import com.kimcompany.jangbogbackendver2.ProductKind.Model.ProductCategoryEntity;
import com.kimcompany.jangbogbackendver2.ProductKind.Repo.ProductCategoryEntityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductKindSelectService {

    private final ProductCategoryEntityRepo productKindRepo;

    public boolean exist(long id){
        ProductCategoryEntity productKindEntity = productKindRepo.findById(id).orElseGet(() -> null);
        if(productKindEntity==null){
            return true;
        }
        return false;
    }
}
