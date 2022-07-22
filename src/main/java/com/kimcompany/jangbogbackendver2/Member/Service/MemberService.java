package com.kimcompany.jangbogbackendver2.Member.Service;

import com.kimcompany.jangbogbackendver2.Member.Repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepo memberRepo;

    public Integer updateSuccessLogin(Long memberId){
        System.out.println( LocalDateTime.now());
        return memberRepo.updatePwdFail(0, memberId, LocalDateTime.now());
    }
    public Integer updateFailLoginTime(String userId){
        return memberRepo.updateFailNum(userId);
    }

}
