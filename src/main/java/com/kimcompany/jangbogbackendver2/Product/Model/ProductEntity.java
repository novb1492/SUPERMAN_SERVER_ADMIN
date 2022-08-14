package com.kimcompany.jangbogbackendver2.Product.Model;

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
@Table(name = "PRODUCT")
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", unique = true)
    private Long id;

    @Embedded
    private CommonColumn commonColumn;

    @Column(name = "CATEGORY",nullable = false,length = 20)
    private String category;

    @Column(name = "ORIGIN",nullable = false,length = 30)
    private String origin;

    @Lob
    @Column(name = "INTRODUCE")
    private String introduce;

    @Column(name = "PRODUCT_IMG_PATH",nullable = false,length = 1000)
    private String productImgPath;


    @Column(name = "PRODUCT_PRICE",nullable = false,length = 20)
    private String price;

    @Column(name = "PRODUCT_NAME",nullable = false,length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID",referencedColumnName = "STORE_ID")
    private StoreEntity storeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADMIN_ID",referencedColumnName = "ADMIN_ID")
    private MemberEntity memberEntity;



}
