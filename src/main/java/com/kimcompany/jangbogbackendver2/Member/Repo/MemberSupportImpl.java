package com.kimcompany.jangbogbackendver2.Member.Repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberSupportImpl implements MemberSupport {

    private final JPAQueryFactory jpaQueryFactory;

}
