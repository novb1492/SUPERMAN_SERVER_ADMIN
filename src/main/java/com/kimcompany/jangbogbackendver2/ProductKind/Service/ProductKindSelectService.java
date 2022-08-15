package com.kimcompany.jangbogbackendver2.ProductKind.Service;

import com.kimcompany.jangbogbackendver2.ProductKind.Model.ProductCategoryEntity;
import com.kimcompany.jangbogbackendver2.ProductKind.Repo.ProductCategoryEntityRepo;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.deleteState;

@Service
@RequiredArgsConstructor
public class ProductKindSelectService {

    private final ProductCategoryEntityRepo productKindRepo;

    public boolean exist(long id){
        ProductCategoryEntity productKindEntity = productKindRepo.findById(id).orElseGet(() -> null);
        if(productKindEntity==null){
            return true;
        }else if(productKindEntity.getCommonColumn().getState()== deleteState){
            return true;
        }
        return false;
    }
}
