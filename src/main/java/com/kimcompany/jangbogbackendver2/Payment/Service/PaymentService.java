package com.kimcompany.jangbogbackendver2.Payment.Service;

import com.kimcompany.jangbogbackendver2.Api.Kg.Dto.RequestCancelPartialDto;
import com.kimcompany.jangbogbackendver2.Api.Kg.Service.KgService;
import com.kimcompany.jangbogbackendver2.Order.Service.OrderSelectService;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final KgService kgService;
    private final OrderSelectService orderSelectService;

    public void refundAll(long cardId) throws NoSuchAlgorithmException {
//        String msg="거래취소요청";
//        RequestCancelPartialDto dto =
//                RequestCancelPartialDto.builder().requestCancelDto(RequestCancelPartialDto.setRequestCancelDto("Card", testTid, msg, BasicText.RefundText)).build();
//        kgService.cancelAllService(dto);
    }
    public void refund(long orderId) throws NoSuchAlgorithmException {
//        RequestCancelPartialDto dto =
//                RequestCancelPartialDto.builder().requestCancelDto(RequestCancelPartialDto.setRequestCancelDto("Card", testTid, msg, BasicText.PartialRefundText))
//                        .price("500").confirmPrice("504").build();
//        kgService.cancelAllService(dto);
    }
}
