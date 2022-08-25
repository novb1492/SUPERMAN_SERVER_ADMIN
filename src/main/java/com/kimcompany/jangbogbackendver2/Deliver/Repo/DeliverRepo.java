package com.kimcompany.jangbogbackendver2.Deliver.Repo;

import com.kimcompany.jangbogbackendver2.Deliver.Model.DeliverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeliverRepo extends JpaRepository<DeliverEntity,Long>,DeliverRepoCustom {


}
