package com.kimcompany.jangbogbackendver2.Store.Repo;

import com.kimcompany.jangbogbackendver2.Store.Dto.QSelectRegiDto;
import com.kimcompany.jangbogbackendver2.Store.Dto.SelectRegiDto;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.kimcompany.jangbogbackendver2.Store.Model.QStoreEntity.storeEntity;

@RequiredArgsConstructor
public class StoreRepoImpl implements StoreRepoCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Boolean exist(String address, String storeName) {
        Integer fetchFirst = jpaQueryFactory
                .selectOne()
                .from(storeEntity)
                .where(storeEntity.name.eq(storeName),storeEntity.addressColumn.address.eq(address))
                .fetchFirst();

        return fetchFirst != null;
    }

    @Override
    public Boolean exist(long storeId, long adminId) {
        Integer fetchFirst = jpaQueryFactory
                .selectOne()
                .from(storeEntity)
                .where(storeEntity.id.eq(storeId),storeEntity.memberEntity.id.eq(adminId))
                .fetchFirst();
        return fetchFirst != null;
    }

    @Override
    public Page<SelectRegiDto> selectForRegi(int page, long adminId,int PageSize) {
        PageRequest pageRequest = PageRequest.of(page-1, PageSize);
        List<SelectRegiDto> selectRegiDtos= jpaQueryFactory
                .select(new QSelectRegiDto(storeEntity))
                .from(storeEntity)
                .where(storeEntity.memberEntity.id.eq(adminId))
                .orderBy(storeEntity.id.desc())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch ();
        // FetchCount
        JPAQuery<Long> count = jpaQueryFactory
                .select(storeEntity.count())
                .from(storeEntity)
                .where(storeEntity.memberEntity.id.eq(adminId));

        // Result
        Page<SelectRegiDto> SelectRegiDto = PageableExecutionUtils.getPage(selectRegiDtos, pageRequest, count::fetchOne);
        return SelectRegiDto;
    }
}
