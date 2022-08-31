package com.kimcompany.jangbogbackendver2.Exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ExceptionControllerTest {

    @Test
    @DisplayName("error catch")
    void test(){
        try {
            throw new NullPointerException("test");
        }catch (InternalError e){
            e.printStackTrace();
        }
    }
}