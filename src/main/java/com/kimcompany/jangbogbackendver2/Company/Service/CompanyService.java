package com.kimcompany.jangbogbackendver2.Company.Service;

import com.kimcompany.jangbogbackendver2.Company.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Company.Model.CompanyEntity;
import com.kimcompany.jangbogbackendver2.Company.Repo.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanySelectService companySelectService;
    private final CompanyRepo companyRepo;
    public void save(TryInsertDto tryInsertDto){
        confirmNum(tryInsertDto.getCompanyNum());
        CompanyEntity companyEntity=TryInsertDto.dtoToEntity(tryInsertDto);
        companyRepo.save(companyEntity);
    }
    public void confirmNum(String num)  {
        if(!companySelectService.existByNum(num)){
            throw new IllegalArgumentException("이미 등록되어있는 사업자 번호입니다");
        }
    }
}
