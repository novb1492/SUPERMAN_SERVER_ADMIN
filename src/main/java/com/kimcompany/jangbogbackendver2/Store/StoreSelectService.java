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

    public boolean checkExist(String address,String storeName,long adminId){
        return storeRepo.exist(address, storeName, adminId);
    }
}
