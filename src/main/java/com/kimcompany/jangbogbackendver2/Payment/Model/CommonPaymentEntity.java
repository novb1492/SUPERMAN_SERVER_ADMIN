package com.kimcompany.jangbogbackendver2.Payment.Model;

import com.kimcompany.jangbogbackendver2.Store.Model.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonPaymentEntity {

    @Column(name = "P_TID",nullable = false,length = 40)
    private String pTid;

    @Column(name = "P_OID",nullable = false,length = 40)
    private String pOid;

    @Column(name = "P_AMT",nullable = false,length = 8)
    private String pAmt;

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
    private String pUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID",referencedColumnName = "STORE_ID")
    private StoreEntity storeEntity;

    /**
     * 취소 관련 변수들
     */
    @Column(name = "prtcCnt",nullable = false)
    private Integer prtcCnt;

    @Column(name = "prtcRemains")
    private Integer prtcRemains;

}
