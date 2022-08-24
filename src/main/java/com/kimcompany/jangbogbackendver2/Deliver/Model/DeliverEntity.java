package com.kimcompany.jangbogbackendver2.Deliver.Model;


import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.Member.Model.ClientEntity;
import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Payment.Model.CardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "DELIVER")
@Entity
public class DeliverEntity {

    @Id
    @Column(name = "DELIVER_ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVER_CLIENT_ID",referencedColumnName = "CLIENT_ID",nullable = false)
    private ClientEntity clientEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVER_CARD_ID",referencedColumnName = "CARD_ID",nullable = false)
    private CardEntity cardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSERT_USER",referencedColumnName = "ADMIN_ID",nullable = false)
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVER_ADMIN_ID", referencedColumnName = "ADMIN_ID", nullable = true)
    private MemberEntity adminEntity;

    @Embedded
    private CommonColumn commonColumn;


}
