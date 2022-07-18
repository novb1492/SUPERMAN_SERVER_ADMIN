package com.kimcompany.jangbogbackendver2.Member.Service;

import com.kimcompany.jangbogbackendver2.Member.Repo.MemberRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDetailsServiceTest {
    @Autowired
    private MemberRepo memberRepo;

    @Test
    public void test(){
        System.out.println(memberRepo.findByEmail("user123").toString());
    }
}