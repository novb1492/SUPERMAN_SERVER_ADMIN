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
    private int cancelPrice;

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
        confirmCount(tryRefundDto.getCount(),refundDto.getOrderEntity().getTotalCount());
        int cancelPrice= tryRefundDto.getCount()*refundDto.getOrderEntity().getPrice();
        confirmPrice(cancelPrice,refundDto.getOrderEntity().getPrice()*refundDto.getOrderEntity().getTotalCount());
        confirmPriceAll(cancelPrice, Integer.parseInt(refundDto.getCardEntity().getP_CARD_APPLPRICE()));
//        RequestCancelPartialDto dto =
//                RequestCancelPartialDto.builder().requestCancelDto(RequestCancelPartialDto.setRequestCancelDto("Card", testTid, msg, BasicText.PartialRefundText))
//                        .price("500").confirmPrice("504").build();
//        kgService.cancelAllService(dto);

    }
    private void confirmPriceAll(int cancelPrice,int cardPrice){
        if(cancelPrice>cardPrice){
            throw new IllegalArgumentException("해당 제품 최대 환불 가능금액은:"+UtilService.confirmPrice(cardPrice)+"원 입니다 " +
                    "\n 요청금액:"+UtilService.confirmPrice(cancelPrice));
        }
    }
    private void confirmPrice(int cancelPrice,int price){
        if(cancelPrice>price){
            throw new IllegalArgumentException("해당 제품 최대 환불 가능금액은:"+UtilService.confirmPrice(price)+"원 입니다 " +
                    "\n 요청금액:"+UtilService.confirmPrice(cancelPrice));
        }
    }
    private void confirmCount(int requestCount,int count){
        if(requestCount>count){
            throw new IllegalArgumentException("해당 제품 최대 환불 가능개수는:"+count+"개 입니다 \n 요청개수:"+requestCount);
        }
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
