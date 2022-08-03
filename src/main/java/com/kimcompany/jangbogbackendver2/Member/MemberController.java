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

    @RequestMapping(value = "/admin/member/info/{userId}",method = GET)
    public ResponseEntity<?>selectInfo(@PathVariable String userId){
        JSONObject response = new JSONObject();
        response.put("data", memberService.selectForRegi(userId));
        return ResponseEntity.ok().body(response);
    }
}
