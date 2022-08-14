package com.kimcompany.jangbogbackendver2.Employee;


import com.kimcompany.jangbogbackendver2.Employee.Dto.TryInsertDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * 직원등록
     * @param tryInsertDto
     * @return
     */
    @RequestMapping(value = "/manage/employee/save",method = POST)
    public ResponseEntity<?>save(@Valid @RequestBody TryInsertDto tryInsertDto){
        employeeService.save(tryInsertDto);
        JSONObject response = new JSONObject();
        response.put("message","종업원 등록에 성공했습니다");
        return ResponseEntity.ok().body(response);
    }

}
