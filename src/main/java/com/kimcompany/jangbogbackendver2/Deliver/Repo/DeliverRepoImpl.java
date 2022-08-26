package com.kimcompany.jangbogbackendver2.Deliver.Repo;

import com.kimcompany.jangbogbackendver2.Deliver.Dto.QSelectListDto;
import com.kimcompany.jangbogbackendver2.Deliver.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Deliver.Dto.SelectListDto;
import com.kimcompany.jangbogbackendver2.Order.Dto.*;
import com.kimcompany.jangbogbackendver2.Order.Repo.OrderRepoCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static com.kimcompany.jangbogbackendver2.Deliver.Model.QDeliverDetailEntity.deliverDetailEntity;
import static com.kimcompany.jangbogbackendver2.Deliver.Model.QDeliverEntity.deliverEntity;
import static com.kimcompany.jangbogbackendver2.Member.Model.QClientEntity.clientEntity;
import static com.kimcompany.jangbogbackendver2.Order.Model.QOrderEntity.orderEntity;
import static com.kimcompany.jangbogbackendver2.Payment.Model.QCardEntity.cardEntity;
import static com.kimcompany.jangbogbackendver2.Product.Model.QProductEntity.productEntity;
import static com.kimcompany.jangbogbackendver2.ProductEvent.Model.QProductEventEntity.productEventEntity;
import static com.kimcompany.jangbogbackendver2.Text.BasicText.*;

@RequiredArgsConstructor
public class DeliverRepoImpl implements DeliverRepoCustom {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<SelectListDto> selectForList(SearchCondition searchCondition) {
        PageRequest pageRequest = PageRequest.of(searchCondition.getPage(), searchCondition.getPageSize());
        List<SelectListDto> fetch = jpaQueryFactory.select(new QSelectListDto(cardEntity, orderEntity, deliverEntity))
                .from(deliverDetailEntity)
                .leftJoin(cardEntity)
                .on(deliverDetailEntity.cardEntity.id.eq(cardEntity.id))
                .leftJoin(orderEntity)
                .on(orderEntity.cardEntity.id.eq(cardEntity.id))
                .leftJoin(deliverEntity)
                .on(deliverDetailEntity.deliverEntity.id.eq(deliverEntity.id))
                .where(deliverDetailEntity.deliverEntity.id.eq(searchCondition.getDeliverId()),deliverEntity.commonColumn.state.eq(searchCondition.getState()), deliverEntity.storeEntity.id.eq(searchCondition.getStoreId()))
                .groupBy(orderEntity.cardEntity.id)
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .orderBy(deliverDetailEntity.id.desc())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(deliverDetailEntity.count())
                .from(deliverDetailEntity)
                .where(deliverDetailEntity.deliverEntity.id.eq(searchCondition.getDeliverId()), deliverEntity.commonColumn.state.eq(searchCondition.getState()));
        return PageableExecutionUtils.getPage(fetch, pageRequest, count::fetchOne);
    }

}
