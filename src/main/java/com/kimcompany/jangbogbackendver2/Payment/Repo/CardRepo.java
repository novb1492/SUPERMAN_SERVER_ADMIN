package com.kimcompany.jangbogbackendver2.Payment.Repo;

import com.kimcompany.jangbogbackendver2.Payment.Model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<CardEntity,Long> {
}
