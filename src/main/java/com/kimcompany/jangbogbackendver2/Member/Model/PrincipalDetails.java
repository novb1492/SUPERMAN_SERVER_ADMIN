package com.kimcompany.jangbogbackendver2.Member.Model;

import com.kimcompany.jangbogbackendver2.Text.BasicText;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {
    private MemberEntity memberEntity;
    private String loginType;

    public PrincipalDetails(MemberEntity memberEntity){
        this.memberEntity=memberEntity;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return memberEntity.getPwd();
    }

    @Override
    public String getUsername() {
        return memberEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return memberEntity.getLastLoginDate().plusYears(BasicText.loginPeriodYear).isAfter(LocalDateTime.now());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return memberEntity.getLastUpdatePwdDate().plusMonths(BasicText.updatePwdPeriodMon).isAfter(LocalDate.now());
    }

    @Override
    public boolean isEnabled() {
        return memberEntity.getCommonColumn().getState() == BasicText.commonState;
    }
}
