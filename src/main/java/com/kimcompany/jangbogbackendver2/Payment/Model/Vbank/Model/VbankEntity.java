package com.kimcompany.jangbogbackendver2.Payment.Model.Vbank.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "VBANK")
@Entity
public class VbankEntity {

    @EmbeddedId
    private VbankEntityPk vbankEntityPk;

    @Column(name = "P_VACT_NUM",nullable = false,length = 14)
    private String P_VACT_NUM;

    @Column(name = "P_VACT_BANK_CODE",nullable = false,length = 2)
    private String P_VACT_BANK_CODE;

    @Column(name = "P_FN_NM",nullable = false,length = 50)
    private String P_FN_NM;

    @Column(name = "P_VACT_DATE",nullable = false,length = 8)
    private String P_VACT_DATE;

    @Column(name = "P_VACT_TIME",nullable = false,length = 6)
    private String P_VACT_TIME;

    @Column(name = "P_VACT_NAME",nullable = false,length = 18)
    private String P_VACT_NAME;


}

