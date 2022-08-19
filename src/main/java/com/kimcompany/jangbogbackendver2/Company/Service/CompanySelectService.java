package com.kimcompany.jangbogbackendver2.Company.Service;

import com.kimcompany.jangbogbackendver2.Company.Dto.SelectListDto;
import com.kimcompany.jangbogbackendver2.Company.Model.CompanyEntity;
import com.kimcompany.jangbogbackendver2.Company.Repo.CompanyRepo;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.deleteState;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanySelectService {
    private final CompanyRepo companyRepo;

    public boolean existByNum(String companyNum){
        CompanyEntity companyEntity = companyRepo.findByCompanyNum(companyNum, deleteState).orElseGet(()->null);
        if(companyEntity==null){
            return true;
        }
        return false;
    }
    public CompanyEntity existByAdminId(long id){
        CompanyEntity companyEntity = companyRepo.findByIdAndAdminId(id, deleteState, UtilService.getLoginUserId()).orElseGet(()->null);
        if(companyEntity==null){
            return null;
        }
        return companyEntity;
    }
    public List<SelectListDto>selectForListNotPaging(long adminId){
        return companyRepo.findByAdminIdForList(adminId, deleteState);
    }

}
