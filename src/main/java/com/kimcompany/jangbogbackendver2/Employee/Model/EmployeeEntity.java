package com.kimcompany.jangbogbackendver2.Employee.Model;


import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Store.Model.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "EMPLOYEE",indexes = {@Index(name = "ADMIN_ID", columnList = "ADMIN_ID")})
@Entity
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID",referencedColumnName = "STORE_ID")
    private StoreEntity storeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_ID",referencedColumnName = "ADMIN_ID")
    private MemberEntity memberEntity;

    @Column(name = "EMPLOYEE_ROLE",nullable = false,length = 10)
    private String role;

    @Embedded
    private CommonColumn commonColumn;
}
