package com.kimcompany.jangbogbackendver2.Order.Repo;

import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Member.Model.PrincipalDetails;
import com.kimcompany.jangbogbackendver2.Member.Model.QClientEntity;
import com.kimcompany.jangbogbackendver2.Order.Dto.QSelectListDto;
import com.kimcompany.jangbogbackendver2.Order.Dto.SearchCondition;
import com.kimcompany.jangbogbackendver2.Order.Dto.SelectListDto;
import com.kimcompany.jangbogbackendver2.Order.Model.QOrderEntity;
import com.kimcompany.jangbogbackendver2.Store.Repo.StoreRepo;
import com.kimcompany.jangbogbackendver2.TestConfig;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.kimcompany.jangbogbackendver2.Member.Model.QClientEntity.clientEntity;
import static com.kimcompany.jangbogbackendver2.Order.Model.QOrderEntity.orderEntity;
import static com.kimcompany.jangbogbackendver2.Store.Model.QStoreEntity.storeEntity;
import static com.kimcompany.jangbogbackendver2.Text.BasicText.closingOfBusinessState;
import static com.kimcompany.jangbogbackendver2.Text.BasicText.orderListPageSize;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
class OrderRepoImplTest {

    private Logger logger = LoggerFactory.getLogger(OrderRepoImplTest.class);

    @Autowired
    private TestEntityManager em;

    @Autowired
    private StoreRepo storeRepo;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void setup() throws IllegalAccessException {
        PrincipalDetails principalDetails=new PrincipalDetails(MemberEntity.builder().id(1L).role("ADMIN").build());
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(principalDetails, principalDetails.getPassword(), principalDetails.getAuthorities()));
    }

    @Test
    @DisplayName("일반 조회 테스트")
    void test(){
        SearchCondition searchCondition = SearchCondition.set(1L, "null", null,1,"false","2022-08-21T10:12:30","2022-08-22T11:12:30");
        query(searchCondition);
    }
    @Test
    @DisplayName("검색 조회 테스트")
    void test2(){
        SearchCondition searchCondition = SearchCondition.set(1L, "addr", "흑",1,"false","2022-08-21T10:12:30","2022-08-22T11:12:30");
        query(searchCondition);
    }
    @Test
    @DisplayName("검색 +기간 조회 테스트")
    void test3(){
        SearchCondition searchCondition = SearchCondition.set(1L, "name", "kim",1,"true","2022-08-21T10:12:30","2022-08-21T11:11:00");
        query(searchCondition);
    }
    private void query(SearchCondition searchCondition){
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
                .fetch();
        // FetchCount
        JPAQuery<Long> count = jpaQueryFactory
                .select(orderEntity.count())
                .from(orderEntity)
                .where(orderEntity.commonColumn.state.eq(searchCondition.getState()), whereDate(searchCondition), whereCategory(searchCondition));
        // Result
        Page<SelectListDto> SelectListDtos = PageableExecutionUtils.getPage(fetch, pageRequest, count::fetchOne);
        logger.info("결과 사이즈:{}",SelectListDtos.getTotalElements());
    }
    private BooleanExpression whereDate( SearchCondition searchCondition) {
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