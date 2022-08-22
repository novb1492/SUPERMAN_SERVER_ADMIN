package com.kimcompany.jangbogbackendver2.Order.Repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepoImpl implements OrderRepoCustom{
    private final JPAQueryFactory jpaQueryFactory;

}
