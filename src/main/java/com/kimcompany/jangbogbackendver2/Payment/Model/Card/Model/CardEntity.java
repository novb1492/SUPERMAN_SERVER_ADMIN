package com.kimcompany.jangbogbackendver2.Payment.Model.Card.Model;

import com.kimcompany.jangbogbackendver2.Payment.Model.PaymentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "CARD")
@Entity
public class CardEntity {

    @EmbeddedId
    private CardEntityPk cardEntityPk;

    @Column(name = "P_CARD_ISSUER_CODE",nullable = false,length = 2)
    private String P_CARD_ISSUER_CODE;

    @Column(name = "P_CARD_MEMBER_NUM",length = 9)
    private String P_CARD_MEMBER_NUM;

    @Column(name = "P_CARD_PURCHASE_CODE",length = 2)
    private String P_CARD_PURCHASE_CODE;

    @Column(name = "P_CARD_PRTC_CODE",nullable = false,length = 1)
    private String P_CARD_PRTC_CODE;

    @Column(name = "P_CARD_INTEREST",length = 1)
    private String P_CARD_INTEREST;

    @Column(name = "CARD_CorpFlag",nullable = false,length = 1)
    private String CARD_CorpFlag;

    @Column(name = "P_CARD_CHECKFLAG",nullable = false,length = 1)
    private String P_CARD_CHECKFLAG;

    @Column(name = "P_RMESG2",length = 500)
    private String P_RMESG2;

    @Column(name = "P_FN_CD1",nullable = false,length = 4)
    private String P_FN_CD1;

    @Column(name = "P_FN_NM",nullable = false,length = 50)
    private String P_FN_NM;

    @Column(name = "P_CARD_NUM",nullable = false,length = 16)
    private String P_CARD_NUM;

    @Column(name = "P_AUTH_NO",length = 30)
    private String P_AUTH_NO;

    @Column(name = "P_ISP_CARDCODE",length = 15)
    private String P_ISP_CARDCODE;

    @Column(name = "P_COUPONFLAG",length = 1)
    private String P_COUPONFLAG;

    @Column(name = "P_COUPON_DISCOUNT",length = 100)
    private String P_COUPON_DISCOUNT;

    @Column(name = "P_CARD_APPLPRICE",length = 100)
    private String P_CARD_APPLPRICE;

    @Column(name = "P_CARD_COUPON_PRICE",nullable = false,length = 50)
    private String P_CARD_COUPON_PRICE;

    @Column(name = "P_SRC_CODE",length = 1)
    private String P_SRC_CODE;

    @Column(name = "P_CARD_USEPOINT",length = 12)
    private String P_CARD_USEPOINT;

    @Column(name = "NAVERPOINT_UseFreePoint",length = 12)
    private String NAVERPOINT_UseFreePoint;

    @Column(name = "NAVERPOINT_CSHRApplYN",length = 1)
    private String NAVERPOINT_CSHRApplYN;

    @Column(name = "NAVERPOINT_CSHRApplAmt",length = 12)
    private String NAVERPOINT_CSHRApplAmt;

    @Column(name = "PCO_OrderNo",length = 500)
    private String PCO_OrderNo;

    @Column(name = "CARD_EmpPrtnCode",length = 1)
    private String CARD_EmpPrtnCode;

    @Column(name = "CARD_NomlMobPrtnCode",length = 1)
    private String CARD_NomlMobPrtnCode;
}
