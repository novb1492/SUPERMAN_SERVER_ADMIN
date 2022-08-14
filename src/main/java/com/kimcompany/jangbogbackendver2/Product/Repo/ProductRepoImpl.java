package com.kimcompany.jangbogbackendver2.Product.Repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.deleteState;
import static com.kimcompany.jangbogbackendver2.Product.Model.QProductEntity.productEntity;

@RequiredArgsConstructor
public class ProductRepoImpl implements ProductRepoCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Boolean exist(long storeId, String productName) {
        Integer fetchFirst = jpaQueryFactory
                .selectOne()
                .from(productEntity)
                .where(productEntity.storeEntity.id.eq(storeId),productEntity.commonColumn.state.ne(deleteState),productEntity.name.eq(productName))
                .fetchFirst();
        return fetchFirst != null;
    }


}
