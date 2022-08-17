package com.kimcompany.jangbogbackendver2.Aop;

import com.kimcompany.jangbogbackendver2.Employee.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Employee.EmployeeSelectService;
import com.kimcompany.jangbogbackendver2.Product.Service.ProductSelectService;
import com.kimcompany.jangbogbackendver2.Store.StoreSelectService;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.*;

@Service
@RequiredArgsConstructor
@Aspect
@Slf4j
public class BeforeSqlAop {
    private final EmployeeSelectService employeeSelectService;
    private final StoreSelectService storeSelectService;

    @Before("execution(* com.kimcompany.jangbogbackendver2.Employee.EmployeeService.save(..))"
            +"||execution(* com.kimcompany.jangbogbackendver2.Product.Service.ProductService.save(..))")
    public void beforeSaveCheck(JoinPoint joinPoint) throws Throwable {
        log.info("트랜잭션전 소유 검사");
        Object[] values=joinPoint.getArgs();
        long storeId = 0;
        for(Object dto:values){
            if(dto instanceof TryInsertDto){
                log.info("종업원 등록전 검사");
                TryInsertDto tryInsertDto=(TryInsertDto)dto;
                storeId = Long.parseLong(tryInsertDto.getStoreId());
                break;
            }else if(dto instanceof com.kimcompany.jangbogbackendver2.Product.Dto.TryInsertDto){
                log.info("상품 등록전 검사");
                com.kimcompany.jangbogbackendver2.Product.Dto.TryInsertDto tryInsertDto=(com.kimcompany.jangbogbackendver2.Product.Dto.TryInsertDto)dto;
                storeId = Long.parseLong(tryInsertDto.getId());
            }
        }
        checkSave(storeId);
    }
    private void checkSave(long storeId){
        long adminId= UtilService.getLoginUserId();
        String role = UtilService.getLoginUserRole();
        if(role.equals(ROLE_ADMIN)){
            if(!storeSelectService.checkExist(storeId,adminId)){
                throw new IllegalArgumentException(cantFindStoreMessage);
            }
        }else if(role.equals(ROLE_MANAGE)){
            if(!employeeSelectService.exist(storeId, adminId, trueStateNum)){
                throw new IllegalArgumentException(cantFindStoreMessage);
            }
        }
    }
}
