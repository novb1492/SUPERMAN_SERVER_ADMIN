package com.kimcompany.jangbogbackendver2.Company.Repo;

import com.kimcompany.jangbogbackendver2.Company.Model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyRepo extends JpaRepository<CompanyEntity,Long> {

    @Query("select c from CompanyEntity c where  c.companyNum=:num and c.commonColumn.state<>:state")
    Optional<CompanyEntity>findByCompanyNum(@Param("num")String num,@Param("state")int state);

    @Query("select c from CompanyEntity c where  c.companyNum=:num and c.commonColumn.state<>:state and c.memberEntity.id=:adminId")
    Optional<CompanyEntity>findByCompanyNum(@Param("num")String num,@Param("state")int state,@Param("adminId")long adminId);
}
