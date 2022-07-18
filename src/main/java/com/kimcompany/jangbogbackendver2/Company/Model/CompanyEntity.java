package com.kimcompany.jangbogbackendver2.Company.Model;

import com.kimcompany.jangbogbackendver2.Common.AddressColumn;
import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.admin.Model.AdminEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "COMPANY")
@Entity
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID", unique = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_ID",referencedColumnName = "ADMIN_ID")
    private AdminEntity adminEntity;

    @Embedded
    private AddressColumn addressColumn;

    @Embedded
    private CommonColumn commonColumn;


}
