package com.kimcompany.jangbogbackendver2.Employee.Model;


import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Store.Model.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 권한은 회원가입시 선택 하면 되게 하였고
 * 권한은 수저은 어드민권한에 넣을 예정입니다
 */
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

    @Embedded
    private CommonColumn commonColumn;
}
