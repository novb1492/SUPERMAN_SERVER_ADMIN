package com.kimcompany.jangbogbackendver2.Order.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class TryRefundDto {

    @NotBlank(message = "환불할 상품을 선택해 주세요")
    private String orderId;

    @Min(value = 1,message = "환불할 상품의 최소 개수는 1개입니다")
    private int count;
}
