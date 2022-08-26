package com.kimcompany.jangbogbackendver2.Deliver.Repo;

import com.kimcompany.jangbogbackendver2.Deliver.Dto.QSelectListDto;
import com.kimcompany.jangbogbackendver2.Deliver.Dto.SelectListDto;
import com.kimcompany.jangbogbackendver2.Deliver.Model.QDeliverDetailEntity;
import com.kimcompany.jangbogbackendver2.Deliver.Model.QDeliverEntity;
import com.kimcompany.jangbogbackendver2.Order.Model.QOrderEntity;
import com.kimcompany.jangbogbackendver2.Payment.Model.QCardEntity;
import com.kimcompany.jangbogbackendver2.TestConfig;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.kimcompany.jangbogbackendver2.Deliver.Model.QDeliverDetailEntity.deliverDetailEntity;
import static com.kimcompany.jangbogbackendver2.Deliver.Model.QDeliverEntity.deliverEntity;
import static com.kimcompany.jangbogbackendver2.Order.Model.QOrderEntity.orderEntity;
import static com.kimcompany.jangbogbackendver2.Payment.Model.QCardEntity.cardEntity;
import static com.kimcompany.jangbogbackendver2.Store.Model.QStoreEntity.storeEntity;
import static com.kimcompany.jangbogbackendver2.Text.BasicText.closingOfBusinessState;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
class DeliverRepoImplTest {

    @Autowired
    private  JPAQueryFactory jpaQueryFactory;

    @Test
    @DisplayName("기본 페이징 쿼리문 작성")
    void test(){
        PageRequest pageRequest = PageRequest.of(1-1, 1);
        List<SelectListDto> fetch = jpaQueryFactory.select(new QSelectListDto(cardEntity, orderEntity, deliverEntity))
                .from(deliverDetailEntity)
                .leftJoin(cardEntity)
                .on(deliverDetailEntity.cardEntity.id.eq(cardEntity.id))
                .leftJoin(orderEntity)
                .on(orderEntity.cardEntity.id.eq(cardEntity.id))
                .leftJoin(deliverEntity)
                .on(deliverDetailEntity.deliverEntity.id.eq(deliverEntity.id))
                .where(deliverDetailEntity.deliverEntity.id.eq(5L), deliverEntity.storeEntity.id.eq(1L))
                .groupBy(orderEntity.cardEntity.id)
                .offset(0)
                .limit(2)
                .orderBy(deliverDetailEntity.id.desc())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(deliverDetailEntity.count())
                .from(deliverDetailEntity)
                .where(deliverDetailEntity.deliverEntity.id.eq(5L));
        // Result
        Page<SelectListDto> SelectListDtos = PageableExecutionUtils.getPage(fetch, pageRequest, count::fetchOne);
        System.out.println(SelectListDtos.getTotalPages());
    }

}