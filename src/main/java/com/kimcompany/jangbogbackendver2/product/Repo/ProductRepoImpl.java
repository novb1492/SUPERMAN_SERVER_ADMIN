package com.kimcompany.jangbogbackendver2.product.Repo;

import com.kimcompany.jangbogbackendver2.Store.Dto.*;
import com.kimcompany.jangbogbackendver2.Store.Repo.StoreRepoCustom;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import com.kimcompany.jangbogbackendver2.product.Model.QProductEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static com.kimcompany.jangbogbackendver2.Employee.Model.QEmployeeEntity.employeeEntity;
import static com.kimcompany.jangbogbackendver2.Store.Model.QStoreEntity.storeEntity;
import static com.kimcompany.jangbogbackendver2.Text.BasicText.closingOfBusinessState;
import static com.kimcompany.jangbogbackendver2.Text.BasicText.deleteState;
import static com.kimcompany.jangbogbackendver2.product.Model.QProductEntity.productEntity;

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
