package com.kimcompany.jangbogbackendver2.Store.Repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.kimcompany.jangbogbackendver2.Store.Model.QStoreEntity.storeEntity;

@RequiredArgsConstructor
public class StoreRepoImpl implements StoreRepoCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Boolean exist(String address, String storeName, long adminId) {
        Integer fetchFirst = jpaQueryFactory
                .selectOne()
                .from(storeEntity)
                .where(storeEntity.name.eq(storeName),storeEntity.addressColumn.address.eq(address))
                .fetchFirst();

        return fetchFirst != null;
    }
}
