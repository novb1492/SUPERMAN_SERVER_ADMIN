package com.kimcompany.jangbogbackendver2.Api.Kg.Dto;

import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.Payment.Model.CardEntity;
import com.kimcompany.jangbogbackendver2.Payment.Model.CommonPaymentEntity;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.trueStateNum;

@NoArgsConstructor
@Data
@Slf4j
public class CardResultDto {
    private String P_TID;
    private String P_CARD_ISSUER_CODE;
    private String P_CARD_MEMBER_NUM;
    private String P_CARD_PURCHASE_CODE;
    private String P_CARD_PRTC_CODE;
    private String P_CARD_INTEREST;
    private String CARD_CorpFlag;
    private String P_CARD_CHECKFLAG;
    private String P_RMESG2;
    private String P_FN_CD1;
    private String P_FN_NM;
    private String P_CARD_NUM;
    private String P_AUTH_NO;
    private String P_ISP_CARDCODE;
    private String P_COUPONFLAG;
    private String P_COUPON_DISCOUNT;
    private String P_CARD_APPLPRICE;
    private String P_CARD_COUPON_PRICE;
    private String P_SRC_CODE;
    private String P_CARD_USEPOINT;
    private String NAVERPOINT_UseFreePoint;
    private String NAVERPOINT_CSHRApplYN;
    private String NAVERPOINT_CSHRApplAmt;
    private String PCO_OrderNo;
    private String CARD_EmpPrtnCode;
    private String CARD_NomlMobPrtnCode;
    private String pAmt;
    private String pType;
    private String pMid;
    private String pAuthDt;
    private String pUserName;
    private String pUserEmail;
    private Long pUserId;
    private String P_OID;

    public static CardEntity dtoToEntity(CardResultDto cardResultDto){

        return CardEntity.builder().CARD_CorpFlag(cardResultDto.getCARD_CorpFlag())
                .CARD_EmpPrtnCode(cardResultDto.getCARD_EmpPrtnCode())
                .CARD_NomlMobPrtnCode(cardResultDto.getCARD_NomlMobPrtnCode())
                .commonColumn(CommonColumn.builder().state(trueStateNum).build())
                .commonPaymentEntity(CommonPaymentEntity.builder().pAmt(cardResultDto.getPAmt()).build()).build();
    }
    /**
     * 결제 결과 dto변환
     * 어자피 클라이언트 구축때 사용 예정이여서 미리구축
     * @param values
     * @return
     */
    public static CardResultDto set(String[] values) {
        CardResultDto cardResultDto = new CardResultDto();
        for (String value : values) {
            if (value.contains("P_TID")) {
                cardResultDto.setP_TID(value.split("=")[1]);
            } else if (value.contains("P_AUTH_DT")) {
                cardResultDto.setPAuthDt(value);
            } else if (value.contains("P_AUTH_NO")) {
                cardResultDto.setP_AUTH_NO(value);
            } else if (value.contains("P_FN_CD1")) {
                cardResultDto.setP_FN_CD1(value);
            } else if (value.contains("P_AMT")) {
                cardResultDto.setPAmt(value);
            } else if (value.contains("P_UNAME")) {
                cardResultDto.setPUserName(value);
            } else if (value.contains("P_MID")) {
                cardResultDto.setPMid(value);
            } else if (value.contains("P_OID")) {
                cardResultDto.setP_OID(value);
            } else if (value.contains("P_CARD_NUM")) {
                cardResultDto.setP_CARD_NUM(value);
            } else if (value.contains("P_CARD_ISSUER_CODE")) {
                cardResultDto.setP_CARD_ISSUER_CODE(value);
            } else if (value.contains("P_CARD_PURCHASE_CODE")) {
                cardResultDto.setP_CARD_PURCHASE_CODE(value);
            } else if (value.contains("P_CARD_PRTC_CODE")) {
                cardResultDto.setP_CARD_PRTC_CODE(value);
            } else if (value.contains("P_CARD_INTEREST")) {
                cardResultDto.setP_CARD_INTEREST(value);
            } else if (value.contains("P_CARD_CHECKFLAG")) {
                cardResultDto.setP_CARD_CHECKFLAG(value);
            }else if (value.contains("P_CARD_ISSUER_NAME")) {
                cardResultDto.setP_CARD_ISSUER_CODE(value);
            }else if(value.contains("P_FN_NM")){
                cardResultDto.setP_FN_NM(value);
            }else if(value.contains("CARD_CorpFlag")){
                cardResultDto.setCARD_CorpFlag(value);
            }else if(value.contains("P_SRC_CODE")){
                cardResultDto.setP_SRC_CODE(value);
            }else if(value.contains("P_CARD_APPLPRICE")){
                cardResultDto.setP_CARD_APPLPRICE(value);
            }
                log.info("val:{}", value);
            }
            return cardResultDto;
        }
    }
