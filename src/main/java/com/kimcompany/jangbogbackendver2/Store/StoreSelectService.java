package com.kimcompany.jangbogbackendver2.Store;

import com.kimcompany.jangbogbackendver2.Store.Repo.StoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreSelectService {

    private final StoreRepo storeRepo;

    public boolean checkExist(String address,String storeName){
        return storeRepo.exist(address, storeName);
    }
    public boolean checkExist(long storeId,long adminId){
        return storeRepo.exist(storeId, adminId);
    }
}
