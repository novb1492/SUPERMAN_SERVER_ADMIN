package com.kimcompany.jangbogbackendver2.Deliver.Model;


import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.Member.Model.ClientEntity;
import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Payment.Model.CardEntity;
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
@Table(name = "DELIVER",indexes = {@Index(name = "STORE_ID_INDEX",columnList = "DELIVER_STORE_ID")})
@Entity
public class DeliverEntity {

    @Id
    @Column(name = "DELIVER_ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVER_STORE_ID",referencedColumnName = "STORE_ID",nullable = false)
    private StoreEntity storeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSERT_USER",referencedColumnName = "ADMIN_ID",nullable = false)
    private MemberEntity memberEntity;

    @Embedded
    private CommonColumn commonColumn;


}
