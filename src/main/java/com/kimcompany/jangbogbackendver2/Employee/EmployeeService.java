package com.kimcompany.jangbogbackendver2.Employee;

import com.kimcompany.jangbogbackendver2.Employee.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Member.Service.MemberSelectService;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.*;

/**
 * 매장에서 직원 관련 서비스입니다
 * role이 3개여서 일단 배열화 하지 않고 검사합니다
 */
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeSelectService employeeSelectService;
    private final MemberSelectService memberSelectService;

    public void save(TryInsertDto tryInsertDto){
        confirmExist(Long.parseLong(tryInsertDto.getUserId()));
        confirmRole(tryInsertDto.getRole());

    }
    private void confirmExist(long userId){
        if(!memberSelectService.exist(userId)){
            throw new IllegalArgumentException("존재 하지 않는 멤버 입니다");
        }
    }
    private void confirmRole(String role){
        if(role.equals(ROLE_ADMIN.equals(role)||ROLE_MANAGE.equals(role)||ROLE_USER.equals(role))){
            return;
        }
        throw new IllegalArgumentException("존재 하지 않는 직급입니다");
    }
}
