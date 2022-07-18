package com.kimcompany.jangbogbackendver2.admin.Model;

import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "ADMIN")
@Entity
public class AdminEntity {

    @Id
    @Column(name = "ADMIN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(name = "ID",nullable = false,updatable = true,length = 50)
    private String id;

    @Column(name = "PWD",nullable = false,length = 100)
    private String pwd;

    @Column(name = "PHONE",nullable = false,length = 20)
    private String phone;

    @Column(name = "ROLE",nullable = false,length = 20)
    private String role;

    @Column(name = "LAST_LOGIN_DATE")
    private Timestamp lastLoginDate;

    @Embedded
    private CommonColumn commonColumn;
}
