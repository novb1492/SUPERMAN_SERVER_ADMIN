package com.kimcompany.jangbogbackendver2.Payment.Model;

import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "PAYMENT")
@Entity
public class PaymentEntity {

    @EmbeddedId
    private PaymentPk paymentPk;

    @Embedded
    private CommonColumn commonColumn;

    @Column(name = "GOOD_NAME",nullable = false,length = 40)
    private String goodName;

    @Column(name = "P_AMT",nullable = false,length = 8)
    private Integer pAmt;

    @Column(name = "P_TYPE",nullable = false,length = 20)
    private String pType;

    @Column(name = "P_MID",nullable = false,length = 10)
    private String pMid;

    @Column(name = "P_AUTH_DT",nullable = false,length = 14)
    private String pAuthDt;

    @Column(name = "P_USER_NAME",nullable = false,length = 30)
    private String pUserName;

    @Column(name = "P_USER_EMAIL",nullable = false,length = 50)
    private String pUserEmail;

    @Column(name = "P_USER_ID",nullable = false)
    private Long pUserId;





}
