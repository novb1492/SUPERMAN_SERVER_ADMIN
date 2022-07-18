package com.kimcompany.jangbogbackendver2.FlyerDetail.Model;

import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.Flyer.Model.FlyerEntity;
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
@Table(name = "FLYER_DETAIL")
@Entity
public class FlyerDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLYER_DETAIL_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FLYER_ID")
    private FlyerEntity flyerEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private StoreEntity storeEntity;

    @Column(name = "FLYER_IMG_PATH",length = 1000,nullable = false)
    private String flyerImgPath;

    @Embedded
    private CommonColumn commonColumn;




}
