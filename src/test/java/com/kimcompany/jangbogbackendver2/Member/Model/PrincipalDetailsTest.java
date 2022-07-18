package com.kimcompany.jangbogbackendver2.Member.Model;

import com.kimcompany.jangbogbackendver2.Text.BasicText;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PrincipalDetailsTest {

    @Test
    public void testPeriod(){
        System.out.println(Timestamp.valueOf("2022-06-05 12:00:00").toLocalDateTime().plusMonths(BasicText.updatePwdPeriodMon));

    }
}