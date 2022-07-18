package com.kimcompany.jangbogbackendver2.Store.Model;

import com.kimcompany.jangbogbackendver2.Common.AddressColumn;
import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.Company.Model.CompanyEntity;
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
@Table(name = "STORE")
@Entity
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID",referencedColumnName = "COMPANY_ID")
    private CompanyEntity companyEntity;

    @Column(name = "MIN_ORDER_PRICE",nullable = false)
    private Integer minOrderPrice;

    @Column(name = "MAX_DELIVER_RADIUS",nullable = false)
    private Integer maxDeliverRadius;

    @Column(name = "OPEN_TIME",nullable = false,length = 4)
    private String openTime;

    @Column(name = "CLOSE_TIME",nullable = false,length = 4)
    private String closeTime;

    @Embedded
    private CommonColumn commonColumn;

    @Embedded
    private AddressColumn addressColumn;




}
