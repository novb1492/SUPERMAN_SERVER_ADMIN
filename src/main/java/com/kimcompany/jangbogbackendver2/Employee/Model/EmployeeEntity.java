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
@Table(name = "EMPLOYEE",indexes = {@Index(name = "EMPLOYEE_NAME", columnList = "EMPLOYEE_NAME"),@Index(name = "EMPLOYEE_PHONE", columnList = "EMPLOYEE_PHONE"),@Index(name = "EMPLOYEE_EMAIL", columnList = "EMPLOYEE_EMAIL")})
@Entity
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID",referencedColumnName = "STORE_ID")
    private StoreEntity storeEntity;

    @Column(name = "EMPLOYEE_NAME",length = 20,nullable = false)
    private String name;

    @Column(name = "EMPLOYEE_PHONE",length = 11,nullable = false)
    private String phone;

    @Column(name = "EMPLOYEE_EMAIL",length = 50,nullable = false)
    private String email;

    @Embedded
    private CommonColumn commonColumn;
}
