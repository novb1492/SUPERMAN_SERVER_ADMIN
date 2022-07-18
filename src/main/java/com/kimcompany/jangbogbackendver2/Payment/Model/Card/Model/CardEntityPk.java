package com.kimcompany.jangbogbackendver2.Payment.Model.Card.Model;

import com.kimcompany.jangbogbackendver2.Payment.Model.PaymentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardEntityPk implements Serializable {

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentEntity paymentEntity;
}
