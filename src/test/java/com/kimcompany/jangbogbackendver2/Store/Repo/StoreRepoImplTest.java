package com.kimcompany.jangbogbackendver2.Store.Repo;

import com.kimcompany.jangbogbackendver2.Store.Dto.SelectRegiDto;
import com.kimcompany.jangbogbackendver2.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
class StoreRepoImplTest {

    private Logger logger = LoggerFactory.getLogger(StoreRepoImplTest.class);

    @Autowired
    private TestEntityManager em;

    @Autowired
    private StoreRepo storeRepo;

    @Test
    @DisplayName("페이징 테스트")
    public void test(){
        Page<SelectRegiDto> selectRegiDtos = storeRepo.selectForRegi(1, 1,2);
        List<SelectRegiDto> selectRegiDtos1 = selectRegiDtos.getContent();
        for(SelectRegiDto s:selectRegiDtos1){
            logger.info("결과:{}",s.toString());
        }
    }
    @Test
    @DisplayName("페이지 초과 테스트")
    public void overPage(){
        Page<SelectRegiDto> selectRegiDtos = storeRepo.selectForRegi(10000, 1,1);
        Assertions.assertThat(selectRegiDtos.getContent()).isEmpty();
    }
}