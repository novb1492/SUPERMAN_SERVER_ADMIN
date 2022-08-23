package com.kimcompany.jangbogbackendver2.Order.Repo;

import com.kimcompany.jangbogbackendver2.Order.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepo extends JpaRepository<OrderEntity,Long>,OrderRepoCustom {

    @Modifying
    @Query("update OrderEntity o set o.commonColumn.state=:state,o.totalCount=:count where o.id=:id and o.storeEntity.id=:storeId")
    Integer updateAfterRefund(@Param("state")int refundState,@Param("count")int count,@Param("id")long id,@Param("storeId")long storeId);
}
