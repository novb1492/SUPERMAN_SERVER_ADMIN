package com.kimcompany.jangbogbackendver2.Deliver.Service;

import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.Deliver.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Deliver.Model.DeliverDetailEntity;
import com.kimcompany.jangbogbackendver2.Deliver.Model.DeliverEntity;
import com.kimcompany.jangbogbackendver2.Deliver.Repo.DeliverDetailRepo;
import com.kimcompany.jangbogbackendver2.Deliver.Repo.DeliverRepo;
import com.kimcompany.jangbogbackendver2.Member.Model.ClientEntity;
import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Payment.Model.CardEntity;
import com.kimcompany.jangbogbackendver2.Payment.Service.CardSelectService;
import com.kimcompany.jangbogbackendver2.Store.Model.StoreEntity;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import com.kimcompany.jangbogbackendver2.Util.EtcService;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.trueStateNum;

@Service
@RequiredArgsConstructor
public class DeliverService {

    private final DeliverSelectService deliverSelectService;
    private final CardSelectService cardSelectService;
    private final EtcService etcService;
    private final DeliverRepo deliverRepo;
    private final DeliverDetailRepo deliverDetailRepo;


    @Transactional(rollbackFor=Exception.class)
    public Long save(TryInsertDto tryInsertDto){
//        etcService.confirmOwn(storeId);
        DeliverEntity deliverEntity = TryInsertDto.dtoToEntity(tryInsertDto);
        long storeId = Long.parseLong(tryInsertDto.getStoreId());
        deliverRepo.save(deliverEntity);
        List<Long> cardIds = tryInsertDto.getCardIds();
        for(long cardId:cardIds){
            CardEntity cardEntity = cardSelectService.selectById(cardId,storeId).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 거래 내역입니다"));
            DeliverDetailEntity deliverDetailEntity=DeliverDetailEntity.builder().deliverEntity(deliverEntity)
                    .cardEntity(cardEntity).clientEntity(ClientEntity.builder().id(Long.parseLong(cardEntity.getCommonPaymentEntity().getPUserId())).build())
                    .commonColumn(CommonColumn.builder().state(trueStateNum).build()).build();
            deliverDetailRepo.save(deliverDetailEntity);
        }
        return deliverEntity.getId();
    }


}
