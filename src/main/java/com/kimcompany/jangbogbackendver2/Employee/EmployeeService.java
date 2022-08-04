package com.kimcompany.jangbogbackendver2.Employee;

import com.kimcompany.jangbogbackendver2.Employee.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Employee.Model.EmployeeEntity;
import com.kimcompany.jangbogbackendver2.Employee.Repo.EmployeeRepo;
import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Member.Service.MemberSelectService;
import com.kimcompany.jangbogbackendver2.Store.StoreSelectService;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.*;
import static com.kimcompany.jangbogbackendver2.Util.UtilService.getLoginUserId;

/**
 * 매장에서 직원 관련 서비스입니다
 */
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final EmployeeSelectService employeeSelectService;
    private final MemberSelectService memberSelectService;
    private final StoreSelectService storeSelectService;


    public void save(TryInsertDto tryInsertDto){
        try{
            long userId = Long.parseLong(tryInsertDto.getUserId());
            long storeId = Long.parseLong(tryInsertDto.getStoreId());
            confirmExistUser(userId);
            confirmExistStore(storeId);
            confirmExist(storeId, userId);
        }catch (ClassCastException e){
            UtilService.ExceptionValue(tryInsertDto.toString(), EmployeeService.class);
            throw new IllegalArgumentException("올바르지 않는 매장직원 혹은 매장입니다");
        }
        EmployeeEntity employeeEntity = TryInsertDto.dtoToEntity(tryInsertDto);
        employeeRepo.save(employeeEntity);
    }
    private void confirmExistStore(long storeId){
        if(!storeSelectService.checkExist(storeId, getLoginUserId())){
            throw new IllegalArgumentException("존재 하지 않는 매장이거나 본인 소유 매장이 아닙니다");
        }
    }
    private void confirmExist(long storeId,long userId){
        if(employeeSelectService.exist(storeId,userId,1)){
            throw new IllegalArgumentException("해당 매장에 이미 등록 되어있는 직원입니다");
        }
    }

    private void confirmExistUser(long userId){
        if(!memberSelectService.exist(userId)){
            throw new IllegalArgumentException("존재 하지 않는 멤버 입니다");
        }
    }
}
