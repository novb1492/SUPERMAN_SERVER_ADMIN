package com.kimcompany.jangbogbackendver2.Order.Repo;

import com.kimcompany.jangbogbackendver2.Order.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity,Long>,OrderRepoCustom {
}
