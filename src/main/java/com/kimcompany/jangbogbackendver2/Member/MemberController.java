package com.kimcompany.jangbogbackendver2.Member;

import com.kimcompany.jangbogbackendver2.Member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 직원등록을 위한 유저조회
     * @param userId
     * @return
     */
    @RequestMapping(value = "/manage/member/info/{userId}",method = GET)
    public ResponseEntity<?>selectInfo(@PathVariable String userId){
        return ResponseEntity.ok().body( memberService.selectForRegi(userId));
    }
}
