package com.kimcompany.jangbogbackendver2.Employee.Repo;

import com.kimcompany.jangbogbackendver2.Store.Dto.InsertEmployNotyDto;
import com.kimcompany.jangbogbackendver2.Store.Repo.StoreRepo;
import com.kimcompany.jangbogbackendver2.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

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

    @Test
    @DisplayName("조인 테스트")
    void test(){
        InsertEmployNotyDto insertEmployNotyDto = employeeRepo.selectStoreAndUser(1, 3);
        logger.info("결과값:{}",insertEmployNotyDto.toString());
    }
}