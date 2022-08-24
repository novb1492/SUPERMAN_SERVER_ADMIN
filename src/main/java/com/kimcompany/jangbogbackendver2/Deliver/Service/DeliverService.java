package com.kimcompany.jangbogbackendver2.Deliver.Service;

import com.kimcompany.jangbogbackendver2.Payment.Model.CardEntity;
import com.kimcompany.jangbogbackendver2.Payment.Service.CardSelectService;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import com.kimcompany.jangbogbackendver2.Util.EtcService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.trueStateNum;

@Service
@RequiredArgsConstructor
public class DeliverService {

    private final DeliverSelectService deliverSelectService;
    private final CardSelectService cardSelectService;
    private final EtcService etcService;

    @Transactional(rollbackFor=Exception.class)
    public void save(long cardId){
        CardEntity cardEntity = cardSelectService.selectById(cardId).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 거래 내역입니다"));
        etcService.confirmOwn(cardEntity.getCommonPaymentEntity().getStoreEntity().getId());
        confirmState(cardEntity.getCommonColumn().getState());

    }
    private void confirmState(int state){
        if(state!= trueStateNum){
            throw new IllegalArgumentException("배달 방에 담을 수 없는 거래 상태입니다 거래상태:"+state);
        }
    }
}
