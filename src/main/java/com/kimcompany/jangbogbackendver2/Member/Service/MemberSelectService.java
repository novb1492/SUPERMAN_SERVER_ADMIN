package com.kimcompany.jangbogbackendver2.Member.Service;

import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Member.Repo.MemberRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberSelectService {
    private final MemberRepo memberRepo;

    public boolean exist(long userId){
        return memberRepo.exist(userId);
    }

    public Optional<MemberEntity> selectByUserId(String userId){
        return memberRepo.findByUserId(userId);
    }
}
