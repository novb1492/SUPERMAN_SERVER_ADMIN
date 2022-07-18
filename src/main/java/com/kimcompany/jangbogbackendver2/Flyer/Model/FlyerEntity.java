package com.kimcompany.jangbogbackendver2.Flyer.Model;

import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.Company.Model.CompanyEntity;
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
@Table(name = "FLYER")
@Entity
public class FlyerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLYER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private StoreEntity storeEntity;

    @Embedded
    private CommonColumn commonColumn;
}
