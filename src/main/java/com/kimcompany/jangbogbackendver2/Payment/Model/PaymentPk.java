package com.kimcompany.jangbogbackendver2.Payment.Model;

import com.kimcompany.jangbogbackendver2.Order.Model.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentPk implements Serializable {

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity orderEntity;

    @Column(name = "P_TID",length = 40)
    private String tid;

    @Column(name = "P_OID",length = 100)
    private String oid;
}
