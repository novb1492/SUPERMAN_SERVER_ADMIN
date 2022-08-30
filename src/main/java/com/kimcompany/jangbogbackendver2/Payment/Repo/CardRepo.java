package com.kimcompany.jangbogbackendver2.Payment.Repo;

import com.kimcompany.jangbogbackendver2.Payment.Dto.SelectForOrderDto;
import com.kimcompany.jangbogbackendver2.Payment.Model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface CardRepo extends JpaRepository<CardEntity,Long> {

    @Query("select new com.kimcompany.jangbogbackendver2.Payment.Dto.SelectForOrderDto(c)" +
            "from CardEntity  c " +
            "where c.commonColumn.state<>:flag and c.id=:id and c.commonPaymentEntity.storeEntity.id=:storeId")
    Optional<SelectForOrderDto>findByIdAndStoreId(@Param("flag")int deleteFlag,@Param("id")long cardId,@Param("storeId")long storeId);

    @Modifying
    @Query("update CardEntity c set c.commonPaymentEntity.prtcRemains=:price,c.commonPaymentEntity.prtcCnt=:count where c.id=:id and c.commonPaymentEntity.storeEntity.id=:storeId")
    Integer updateAfterRefund(@Param("price") int price, @Param("id") long cardId,@Param("storeId")long storeId,@Param("count") int count);

    @Query("select c from  CardEntity c where c.commonColumn.state<>:state and c.id=:id")
    Optional<CardEntity> findByIdForRefundAll(@Param("id") long cardId, @Param("state") int deleteFlag);

    @Modifying
    @Query("update CardEntity c set c.commonColumn=:state where c.commonColumn.state<>:deleteState and c.id=:id and c.commonPaymentEntity.storeEntity=:storeId")
    Integer updateState(@Param("deleteState") int deleteState, @Param("state") int state, @Param("id") long cardId, @Param("storeId") long storeId);

    @Query("select c from CardEntity c where c.commonColumn.state=:state and c.id=:id and c.commonPaymentEntity.storeEntity.id=:storeId")
    Optional<CardEntity> findByIdNotDelete(@Param("id") long id, @Param("state") int trueState,@Param("storeId") long storeId);

//    @Query("select c from CardEntity c where c.commonColumn.state<>:state and c.commonPaymentEntity.storeEntity=:storeId and c.commonColumn.created")
}
