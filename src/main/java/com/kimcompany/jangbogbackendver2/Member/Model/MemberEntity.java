package com.kimcompany.jangbogbackendver2.Member.Model;

import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "MEMBER")
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "EMAIL",nullable = false,length = 50)
    private String email;

    @Column(name = "PWD",nullable = false,length = 1000)
    private String pwd;

    @Column(name = "FIRST_NAME",nullable = false,length = 20)
    private String firstName;

    @Column(name = "LAST_NAME",nullable = false,length = 20)
    private String lastName;

    @Column(name = "PHONE",nullable = false,length = 20)
    private String phone;

    @Column(name = "ADDR",nullable = false,length = 50)
    private String addr;

    @Embedded
    private CommonColumn commonColumn;

    @Column(name = "LAST_LOGIN_DATE")
    private LocalDateTime lastLoginDate;

    @Column(name = "BIRTH",nullable = false)
    private LocalDate birth;

    @Column(name = "Last_UPDATE_PWD_DATE",nullable = false)
    private LocalDate lastUpdatePwdDate;

    @Override
    public String toString() {
        return "MemberEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", addr='" + addr + '\'' +
                ", commonColumn=" + commonColumn +
                ", lastLoginDate=" + lastLoginDate +
                ", birth=" + birth +
                ", lastUpdatePwdDate=" + lastUpdatePwdDate +
                '}';
    }
}
