package com.kimcompany.jangbogbackendver2.Company.Service;

import com.kimcompany.jangbogbackendver2.Api.JungBu;
import com.kimcompany.jangbogbackendver2.Company.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Company.Model.CompanyEntity;
import com.kimcompany.jangbogbackendver2.Company.Repo.CompanyRepo;
import com.kimcompany.jangbogbackendver2.Text.PropertiesText;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanySelectService companySelectService;
    private final CompanyRepo companyRepo;
    public void save(TryInsertDto tryInsertDto) throws ParseException {
        confirmNum(tryInsertDto.getCompanyNum());
        CompanyEntity companyEntity=TryInsertDto.dtoToEntity(tryInsertDto);
        companyRepo.save(companyEntity);
    }
    public void confirmNum(String num) throws ParseException {
        if(!companySelectService.existByNum(num)){
            throw new IllegalArgumentException("이미 등록되어있는 사업자 번호입니다");
        }
        JSONObject response = JungBu.getCompanyNum(num, PropertiesText.homeTaxApiKey);
        if(response==null){
            throw new InternalError("알 수 없는 에러 발생");
        }
        JSONArray dataArr= (JSONArray) response.get("data");
        JSONObject data= UtilService.stringToJson(dataArr.get(0).toString());
        if(!response.containsKey("match_cnt")){
            throw new IllegalArgumentException(data.get("tax_type").toString());
        }else if(!data.get("b_stt_cd").equals("01")){
            throw new IllegalArgumentException(data.get("b_stt").toString());
        }
    }

}
