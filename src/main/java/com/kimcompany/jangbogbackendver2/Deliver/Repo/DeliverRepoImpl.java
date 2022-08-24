package com.kimcompany.jangbogbackendver2.Deliver.Repo;

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

import static com.kimcompany.jangbogbackendver2.Member.Model.QClientEntity.clientEntity;
import static com.kimcompany.jangbogbackendver2.Order.Model.QOrderEntity.orderEntity;
import static com.kimcompany.jangbogbackendver2.Payment.Model.QCardEntity.cardEntity;
import static com.kimcompany.jangbogbackendver2.Product.Model.QProductEntity.productEntity;
import static com.kimcompany.jangbogbackendver2.ProductEvent.Model.QProductEventEntity.productEventEntity;
import static com.kimcompany.jangbogbackendver2.Text.BasicText.*;

@RequiredArgsConstructor
public class DeliverRepoImpl implements DeliverRepoCustom {
    private final JPAQueryFactory jpaQueryFactory;



}
