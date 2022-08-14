package com.kimcompany.jangbogbackendver2.product.Repo;

import com.kimcompany.jangbogbackendver2.Store.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Store.Dto.SelectInfo;
import com.kimcompany.jangbogbackendver2.Store.Dto.SelectListDto;
import com.kimcompany.jangbogbackendver2.Store.Dto.SelectRegiDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductRepoCustom {
    Boolean exist(long storeId, String productName);


}
