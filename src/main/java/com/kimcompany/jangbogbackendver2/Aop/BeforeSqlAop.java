package com.kimcompany.jangbogbackendver2.Aop;

import com.kimcompany.jangbogbackendver2.Employee.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Employee.EmployeeSelectService;
import com.kimcompany.jangbogbackendver2.Order.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Product.Service.ProductSelectService;
import com.kimcompany.jangbogbackendver2.Store.StoreSelectService;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import com.kimcompany.jangbogbackendver2.Util.EtcService;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.*;

/**
 * 해당 매장의 접근 권리가 있는지 확인하는 aop입니다
 */
@Component
@RequiredArgsConstructor
@Aspect
@Slf4j
public class BeforeSqlAop {
    private final EtcService etcService;
    /**
     * 상품/직원등록전 해당 매장에 대한
     * 권리가 있는지 확인
     * @param joinPoint
     * @throws Throwable
     */
    @Before("execution(* com.kimcompany.jangbogbackendver2.Employee.EmployeeService.save(..))"
            +"||execution(* com.kimcompany.jangbogbackendver2.Product.Service.ProductService.save(..))"
            +"||execution(* com.kimcompany.jangbogbackendver2.Deliver.Service.DeliverService.save(..))")
    public void beforeSaveCheck(JoinPoint joinPoint) throws Throwable {
        log.info("save전 소유 검사");
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
            }else if(dto instanceof com.kimcompany.jangbogbackendver2.Deliver.Dto.TryInsertDto ){
                log.info("배달방 등록전 검사");
                com.kimcompany.jangbogbackendver2.Deliver.Dto.TryInsertDto tryInsertDto = (com.kimcompany.jangbogbackendver2.Deliver.Dto.TryInsertDto) dto;
                storeId =Long.parseLong(tryInsertDto.getStoreId());
            }
        }
        etcService.confirmOwn(storeId);
    }

    /**
     * 해당 매장의 주문/직원/상품/배달/매출 조회전
     * 권한이있는지 확인
     * @param joinPoint
     * @throws Throwable
     */
    @Before("execution(* com.kimcompany.jangbogbackendver2.Product.Service.ProductService.selectForList(..))"
//            +"||execution(* com.kimcompany.jangbogbackendver2.Order.Service.OrderService.selectForList(..))"
    )
    public void checkBelong(JoinPoint joinPoint) throws Throwable{
        log.info("select전 소유 검사");
        long storeId = 0;
        for (Object obj : joinPoint.getArgs()) {
            if (obj instanceof Long) {
                storeId =  (long) obj;
                break;
            }
        }
        etcService.confirmOwn(storeId);
    }

}
