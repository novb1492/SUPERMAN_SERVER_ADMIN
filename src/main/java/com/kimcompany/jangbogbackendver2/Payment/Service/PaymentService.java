package com.kimcompany.jangbogbackendver2.Payment.Service;

import com.kimcompany.jangbogbackendver2.Api.Kg.Dto.RequestCancelPartialDto;
import com.kimcompany.jangbogbackendver2.Api.Kg.Service.KgService;
import com.kimcompany.jangbogbackendver2.Employee.EmployeeSelectService;
import com.kimcompany.jangbogbackendver2.Order.Dto.RefundDto;
import com.kimcompany.jangbogbackendver2.Order.Dto.TryRefundDto;
import com.kimcompany.jangbogbackendver2.Order.Service.OrderSelectService;
import com.kimcompany.jangbogbackendver2.Store.StoreSelectService;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.*;
import static com.kimcompany.jangbogbackendver2.Text.BasicText.cantFindStoreMessage;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final KgService kgService;
    private final OrderSelectService orderSelectService;
    private final StoreSelectService storeSelectService;
    private final EmployeeSelectService employeeSelectService;

    public void refundAll(long cardId) throws NoSuchAlgorithmException {
//        String msg="거래취소요청";
//        RequestCancelPartialDto dto =
//                RequestCancelPartialDto.builder().requestCancelDto(RequestCancelPartialDto.setRequestCancelDto("Card", testTid, msg, BasicText.RefundText)).build();
//        kgService.cancelAllService(dto);
    }
    public void refund(TryRefundDto tryRefundDto) throws NoSuchAlgorithmException {
        long orderId = Long.parseLong(tryRefundDto.getOrderId());
        RefundDto refundDto = orderSelectService.selectForRefund(orderId).orElseThrow(() -> new IllegalArgumentException("찾을 수없는 주문정보 입니다"));
        confirmOwn(refundDto.getCardEntity().getCommonPaymentEntity().getStoreEntity().getId());
        confirmOwn(refundDto.getOrderEntity().getStoreEntity().getId());
//        RequestCancelPartialDto dto =
//                RequestCancelPartialDto.builder().requestCancelDto(RequestCancelPartialDto.setRequestCancelDto("Card", testTid, msg, BasicText.PartialRefundText))
//                        .price("500").confirmPrice("504").build();
//        kgService.cancelAllService(dto);

    }
    private void confirmOwn(long storeId){
        long adminId= UtilService.getLoginUserId();
        String role = UtilService.getLoginUserRole();
        if(role.equals(ROLE_ADMIN)){
            if(!storeSelectService.checkExist(storeId,adminId)){
                throw new IllegalArgumentException(cantFindStoreMessage);
            }
        }else if(role.equals(ROLE_MANAGE)||role.equals(ROLE_USER)){
            if(!employeeSelectService.exist(storeId, adminId, trueStateNum)){
                throw new IllegalArgumentException(cantFindStoreMessage);
            }
        }
    }
}
