package com.kimcompany.jangbogbackendver2.Product.Service;

import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Member.Model.PrincipalDetails;
import com.kimcompany.jangbogbackendver2.Product.Dto.TryInsertDto;
import com.kimcompany.jangbogbackendver2.Text.BasicText;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("ADMIN 정상 제품등록")
    @WithUserDetails("kim")
    @Transactional
    void test(){
        TryInsertDto tryInsertDto=set("1","13,000","test소고기");
        productService.save(tryInsertDto);
    }
    @Test
    @DisplayName("MANAGE 정상 제품등록")
    @WithUserDetails("kim3")
    @Transactional
    void test2(){
        TryInsertDto tryInsertDto=set("1","13,000","test소고기");
        productService.save(tryInsertDto);
    }
    @Test
    @DisplayName("USER 정상 제품등록")
    @WithUserDetails("kim2")
    @Transactional
    void test3(){
        TryInsertDto tryInsertDto=set("2","13,000","test소고기");
        productService.save(tryInsertDto);
    }
    @Test
    @DisplayName("해당 소유의 매장이 아닐경우")
    @Transactional
    void test4(){
        setUser(3L, BasicText.ROLE_USER);
        TryInsertDto tryInsertDto=set("1","13,000","test소고기");
        assertThrows(IllegalArgumentException.class, () ->  productService.save(tryInsertDto));
        setUser(4L,BasicText.ROLE_MANAGE);
        TryInsertDto tryInsertDto2=set("2","13,000","test소고기");
        assertThrows(IllegalArgumentException.class, () ->  productService.save(tryInsertDto2));
    }
    @Test
    @DisplayName("금액에  오류가 있을경우")
    @WithUserDetails("kim")
    @Transactional
    void test5(){
        TryInsertDto tryInsertDto=set("1","ㅎㅇㅀㅇ","test소고기");
        assertThrows(IllegalArgumentException.class, () ->  productService.save(tryInsertDto));
    }
    private void setUser(long id ,String role){
        PrincipalDetails principalDetails=new PrincipalDetails(MemberEntity.builder().id(id).role(role).build());
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(principalDetails, principalDetails.getPassword(), principalDetails.getAuthorities()));
    }
    private TryInsertDto set(String storeId,String price,String name){
        TryInsertDto tryInsertDto=new TryInsertDto();
        tryInsertDto.setCategory("정육");
        tryInsertDto.setId(storeId);
        tryInsertDto.setIntroduce("제품소개");
        tryInsertDto.setName(name);
        tryInsertDto.setOrigin("국내산");
        tryInsertDto.setPrice(price);
        tryInsertDto.setProductImgPath("테스트이미지");
        return tryInsertDto;
    }
}