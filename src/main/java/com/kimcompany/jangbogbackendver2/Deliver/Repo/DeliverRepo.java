package com.kimcompany.jangbogbackendver2.Deliver.Repo;

import com.kimcompany.jangbogbackendver2.Deliver.Model.DeliverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliverRepo extends JpaRepository<DeliverEntity,Long>,DeliverRepoCustom {
}
