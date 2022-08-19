package com.kimcompany.jangbogbackendver2.Employee.Repo;

import com.kimcompany.jangbogbackendver2.Employee.Dto.NotyEmployeeDto;
import com.kimcompany.jangbogbackendver2.Employee.Dto.QNotyEmployeeDto;
import com.kimcompany.jangbogbackendver2.Store.Dto.InsertEmployNotyDto;
import com.kimcompany.jangbogbackendver2.Store.Repo.StoreRepo;
import com.kimcompany.jangbogbackendver2.TestConfig;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static com.kimcompany.jangbogbackendver2.Employee.Model.QEmployeeEntity.employeeEntity;
import static com.kimcompany.jangbogbackendver2.Member.Model.QMemberEntity.memberEntity;
import static com.kimcompany.jangbogbackendver2.Text.BasicText.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
class EmployeeCustomImplTest {

    private Logger logger = LoggerFactory.getLogger(EmployeeCustomImplTest.class);

    @Autowired
    private TestEntityManager em;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    @DisplayName("조인 테스트")
    void test(){
        InsertEmployNotyDto insertEmployNotyDto = employeeRepo.selectStoreAndUser(1, 3);
        logger.info("결과값:{}",insertEmployNotyDto.toString());
    }

    @Test
    @DisplayName("소속매장 매니저 어드민 전화/이메일 꺼내기")
    void test2(){
        List<NotyEmployeeDto> fetch = jpaQueryFactory.select(new QNotyEmployeeDto(memberEntity.phone, memberEntity.email))
                .from(memberEntity)
                .innerJoin(employeeEntity)
                .on(employeeEntity.memberEntity.id.eq(memberEntity.id))
                .where(employeeEntity.commonColumn.state.eq(trueStateNum), employeeEntity.storeEntity.id.eq(1L)
                        , memberEntity.role.eq(ROLE_ADMIN).or(memberEntity.role.eq(ROLE_MANAGE)))
                .fetch();
        for(NotyEmployeeDto dto:fetch){
            logger.info("결과:{}",dto.getEmail());
        }
    }
}