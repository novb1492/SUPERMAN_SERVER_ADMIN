package com.kimcompany.jangbogbackendver2.Employee;


import com.kimcompany.jangbogbackendver2.Employee.Dto.TryInsertDto;
import lombok.RequiredArgsConstructor;
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

    @RequestMapping(value = "/manage/employee/save",method = POST)
    public ResponseEntity<?>save(@Valid @RequestBody TryInsertDto tryInsertDto){
        return null;
    }
}
