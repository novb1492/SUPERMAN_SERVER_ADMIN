package com.kimcompany.jangbogbackendver2.Store.Repo;

import com.kimcompany.jangbogbackendver2.Store.Model.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<StoreEntity,Long>,StoreRepoCustom {
    
}
