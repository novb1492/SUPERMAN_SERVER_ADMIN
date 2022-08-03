package com.kimcompany.jangbogbackendver2.Store;

import com.kimcompany.jangbogbackendver2.Store.Dto.SelectRegiDto;
import com.kimcompany.jangbogbackendver2.Store.Repo.StoreRepo;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.regiEmployeePageSize;
import static com.kimcompany.jangbogbackendver2.Util.UtilService.getLoginUserId;

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

    public Page<SelectRegiDto> selectForRegi(int page,int pageSize){
        return storeRepo.selectForRegi(page, getLoginUserId(), pageSize);
    }

}
