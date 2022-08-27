package com.kimcompany.jangbogbackendver2.Deliver.Service;

import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.Deliver.Dto.SelectDto;

import com.kimcompany.jangbogbackendver2.Deliver.Dto.StartDeliverDto;
import com.kimcompany.jangbogbackendver2.Deliver.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Deliver.Model.DeliverDetailEntity;
import com.kimcompany.jangbogbackendver2.Deliver.Model.DeliverEntity;
import com.kimcompany.jangbogbackendver2.Deliver.Repo.DeliverDetailRepo;
import com.kimcompany.jangbogbackendver2.Deliver.Repo.DeliverRepo;
import com.kimcompany.jangbogbackendver2.Member.Model.ClientEntity;
import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Order.Repo.OrderRepo;
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

import java.sql.SQLException;
import java.util.List;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.*;

@Service
@RequiredArgsConstructor
public class DeliverService {

    private final DeliverSelectService deliverSelectService;
    private final CardSelectService cardSelectService;
    private final EtcService etcService;
    private final DeliverRepo deliverRepo;
    private final DeliverDetailRepo deliverDetailRepo;
    private final OrderRepo orderRepo;


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
    public List<SelectDto>selectForDetail(long storeId, long deliverId){
        etcService.confirmOwn(storeId);
        return deliverSelectService.selectForDetail(storeId, deliverId);
    }
    @Transactional(rollbackFor = Exception.class)
    public void updateDeliverAndDeliverDetailAndOrderState(StartDeliverDto startDeliverDto) throws SQLException {
        long deliverId = startDeliverDto.getDeliverId();
        int state = startDeliverDto.getState();
        long storeId = startDeliverDto.getStoreId();
        if(state!=deliveringState&&state!=deliverCancelState&&state!=deliverDoneState){
            throw new IllegalArgumentException("올바르지 않는 스테이트 값입니다");
        }
        int a=deliverRepo.updateState(deliverId,state,storeId);
        int b=deliverDetailRepo.updateStateByDeliverId(state, deliverId);
        DeliverDetailEntity deliverDetailEntity = deliverRepo.selectByDeliverId(deliverId).orElseThrow(() -> new IllegalArgumentException("조회 할 수없는 배달 입니다"));
        int c=orderRepo.updateStateByCardId(state,deliverDetailEntity.getCardEntity().getId(),storeId);
        if(a==0||b==0||c==0){
            throw new SQLException("상태갱신 실패");
        }

    }



}
