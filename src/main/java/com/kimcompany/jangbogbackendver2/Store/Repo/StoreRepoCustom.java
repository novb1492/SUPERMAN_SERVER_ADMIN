package com.kimcompany.jangbogbackendver2.Store.Repo;

import com.kimcompany.jangbogbackendver2.Store.Dto.SelectRegiDto;
import org.springframework.data.domain.Page;

public interface StoreRepoCustom {
    Boolean exist(String address, String storeName);
    Boolean exist(long storeId, long adminId);

    Page<SelectRegiDto> selectForRegi(int page, long adminId,int pageSize);


}
