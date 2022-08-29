package com.kimcompany.jangbogbackendver2.Deliver.Repo;

import com.kimcompany.jangbogbackendver2.Deliver.Model.DeliverDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeliverDetailRepo extends JpaRepository<DeliverDetailEntity,Long> {

    @Modifying
    @Query("update DeliverDetailEntity d set d.commonColumn.state=:state where d.deliverEntity.id=:deliverId")
    Integer updateStateByDeliverId(@Param("state") int state, @Param("deliverId") long deliverId);

    @Modifying
    @Query("update DeliverDetailEntity d set d.commonColumn.state=:state where d.id=:id")
    Integer updateDetailState(@Param("state") int state, @Param("id") long deliverDetailId);


}
