package com.kimcompany.jangbogbackendver2.Order.Repo;

import com.kimcompany.jangbogbackendver2.Order.Dto.QSelectListDto;
import com.kimcompany.jangbogbackendver2.Order.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Order.Dto.SelectListDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;

import java.sql.Timestamp;
import java.util.List;

import static com.kimcompany.jangbogbackendver2.Member.Model.QClientEntity.clientEntity;
import static com.kimcompany.jangbogbackendver2.Order.Model.QOrderEntity.orderEntity;
import static com.kimcompany.jangbogbackendver2.Text.BasicText.orderListPageSize;

@RequiredArgsConstructor
public class OrderRepoImpl implements OrderRepoCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public Page<SelectListDto> selectForList(SearchCondition searchCondition){
        PageRequest pageRequest = PageRequest.of(0, orderListPageSize);
        List<SelectListDto> fetch = jpaQueryFactory.select(new QSelectListDto(orderEntity, clientEntity))
                .from(orderEntity)
                .leftJoin(clientEntity)
                .on(orderEntity.clientEntity.id.eq(clientEntity.id))
                .fetchJoin()
                .where(orderEntity.commonColumn.state.eq(searchCondition.getState()), whereDate(searchCondition), whereCategory(searchCondition))
                .orderBy(orderEntity.id.desc())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .groupBy(orderEntity.cardEntity.id)
                .fetch();
        // FetchCount
        JPAQuery<Long> count = jpaQueryFactory
                .select(orderEntity.count())
                .from(orderEntity)
                .where(orderEntity.commonColumn.state.eq(searchCondition.getState()), whereDate(searchCondition), whereCategory(searchCondition));
        // Result
        Page<SelectListDto> SelectListDtos = PageableExecutionUtils.getPage(fetch, pageRequest, count::fetchOne);
        return SelectListDtos;
    }
    private BooleanExpression whereDate(SearchCondition searchCondition) {
        if (searchCondition.getPeriodFlag().equals("true")) {
            return orderEntity.commonColumn.created.between(Timestamp.valueOf(searchCondition.getStartDate()).toLocalDateTime()
                    , (Timestamp.valueOf(searchCondition.getEndDate()).toLocalDateTime()));
        }
        return null;
    }
    private BooleanExpression whereCategory( SearchCondition searchCondition) {
        String category = searchCondition.getCategory();
        if (category.equals("name")) {
            return orderEntity.clientEntity.fullName.contains(searchCondition.getKeyword());
        }else if(category.equals("addr")){
            return orderEntity.addressColumn.address.contains(searchCondition.getKeyword());
        }
        return null;
    }

}
