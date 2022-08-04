package com.kimcompany.jangbogbackendver2.Employee.Repo;

import com.kimcompany.jangbogbackendver2.Employee.Model.QEmployeeEntity;
import com.kimcompany.jangbogbackendver2.Member.Model.QMemberEntity;
import com.kimcompany.jangbogbackendver2.Store.Dto.InsertEmployNotyDto;
import com.kimcompany.jangbogbackendver2.Store.Dto.QInsertEmployNotyDto;
import com.kimcompany.jangbogbackendver2.Store.Model.QStoreEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.kimcompany.jangbogbackendver2.Employee.Model.QEmployeeEntity.employeeEntity;
import static com.kimcompany.jangbogbackendver2.Member.Model.QMemberEntity.memberEntity;
import static com.kimcompany.jangbogbackendver2.Store.Model.QStoreEntity.storeEntity;

@RequiredArgsConstructor
public class EmployeeCustomImpl implements EmployeeCustom{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public boolean exist(long storeId, long userId,int state) {
        Integer fetchFirst = jpaQueryFactory
                .selectOne()
                .from(employeeEntity)
                .where(employeeEntity.storeEntity.id.eq(storeId),employeeEntity.memberEntity.id.eq(userId),employeeEntity.commonColumn.state.eq(state))
                .fetchFirst();
        return fetchFirst != null;
    }

    @Override
    public InsertEmployNotyDto selectStoreAndUser(long storeId, long userId) {
        return  jpaQueryFactory
                .select(new QInsertEmployNotyDto(memberEntity, storeEntity))
                .from(storeEntity)
                .leftJoin(memberEntity)
                .on(storeEntity.id.eq(storeId))
                .where(storeEntity.id.eq(storeId),memberEntity.id.eq(userId))
                .fetchFirst();
    }
}
