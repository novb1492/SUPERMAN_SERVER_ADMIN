package com.kimcompany.jangbogbackendver2.Basket.Model;

import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Store.Model.StoreEntity;
import com.kimcompany.jangbogbackendver2.product.Event.Model.ProductEventEntity;
import com.kimcompany.jangbogbackendver2.product.Model.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "BASKET")
@Entity
public class BasketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BASKET_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID",referencedColumnName = "PRODUCT_ID")
    private ProductEntity productEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private MemberEntity memberEntity;

    @Column(name = "CREATED")
    @CreatedDate
    private LocalDateTime created;

    @Column(name = "PRICE",nullable = false)
    private int price;

    @Column(name = "TOTAL_COUNT",nullable = false)
    private int totalCount;

}
