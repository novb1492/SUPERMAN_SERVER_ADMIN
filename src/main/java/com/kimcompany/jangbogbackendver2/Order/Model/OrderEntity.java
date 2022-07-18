package com.kimcompany.jangbogbackendver2.Order.Model;

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
@Table(name = "ORDERS")
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private StoreEntity storeEntity;

    @Column(name = "MEMBER_ID",nullable = false)
    private Long memberId;

    @Column(name = "PRICE",nullable = false)
    private Integer price;

    @Column(name = "TOTAL_COUNT",nullable = false)
    private Integer totalCount;

    @Column(name = "EVENT")
    private Integer event;

    @Embedded
    private CommonColumn commonColumn;

}
