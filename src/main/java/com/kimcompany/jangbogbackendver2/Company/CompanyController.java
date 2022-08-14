package com.kimcompany.jangbogbackendver2.Company;

import com.kimcompany.jangbogbackendver2.Company.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Company.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    /**
     * 사업자번호 등록
     */
    @RequestMapping(value = "/admin/company/save",method = RequestMethod.POST)
    public ResponseEntity<?>save(@Valid @RequestBody TryInsertDto tryInsertDto) throws ParseException {
        companyService.save(tryInsertDto);
        JSONObject response=new JSONObject();
        response.put("message","등록성공");
        return ResponseEntity.ok().body(response);
    }
}
