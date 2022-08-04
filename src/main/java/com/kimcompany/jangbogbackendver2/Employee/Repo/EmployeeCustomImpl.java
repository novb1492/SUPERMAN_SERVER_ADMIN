package com.kimcompany.jangbogbackendver2.Employee.Repo;

import com.kimcompany.jangbogbackendver2.Employee.Model.QEmployeeEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.kimcompany.jangbogbackendver2.Employee.Model.QEmployeeEntity.employeeEntity;

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
}
