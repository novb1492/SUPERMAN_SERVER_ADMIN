package com.kimcompany.jangbogbackendver2.Member.Repo;

import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MemberRepo extends JpaRepository<MemberEntity,Long>, MemberSupport {

    @Query("SELECT m from MemberEntity  m where m.email=:email")
    Optional<MemberEntity> findByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("UPDATE MemberEntity m SET m.lastLoginDate=:now WHERE m.email=:email")
    void updateLoginDate(@Param("now")LocalDateTime now ,@Param("email")String email);

}
