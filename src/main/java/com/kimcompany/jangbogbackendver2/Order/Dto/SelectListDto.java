package com.kimcompany.jangbogbackendver2.Order.Dto;

import com.kimcompany.jangbogbackendver2.Member.Model.ClientEntity;
import com.kimcompany.jangbogbackendver2.Order.Model.OrderEntity;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class SelectListDto {

    private long id;
    private String orderOwnName;
    private LocalDateTime orderDate;
    private String destinationPostCode;
    private String destinationAddr;
    private String destinationDetailAddr;
    private String orderOwnPhone;

    @QueryProjection
    public SelectListDto(OrderEntity orderEntity, ClientEntity clientEntity) {
        this.id = orderEntity.getId();
        this.orderOwnName = clientEntity.getFullName();
        this.orderOwnPhone=clientEntity.getPhone();
        this.orderDate = orderEntity.getCommonColumn().getCreated();
        this.destinationPostCode = orderEntity.getAddressColumn().getPostCode();
        this.destinationAddr =  orderEntity.getAddressColumn().getAddress();
        this.destinationDetailAddr =  orderEntity.getAddressColumn().getDetailAddress();
    }
}
