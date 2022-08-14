package com.kimcompany.jangbogbackendver2.product.Dto;

import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.Store.Model.StoreEntity;
import com.kimcompany.jangbogbackendver2.product.Model.ProductEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class TryInsertDto {

    @NotBlank(message = "상품 카테고리를 선택해주세요")
    private String category;

    @NotBlank(message = "상품 원산지를 적어주세요")
    private String origin;

    @NotBlank(message = "상품 설명을 적어주세요")
    private String introduce;

    @NotBlank(message = "상품 이미지를 올려주세요")
    private String productImgPath;

    @Min(value = 100,message = "최소금액은 100원입니다")
    private int price;

    @NotBlank(message = "상품 이름을 적어주세요")
    private String name;

    @NotBlank(message = "매장정보를 유실했습니다")
    private String id;

    /**
     * 상품의 이벤트 내용 null가능
     */
    private Integer eventPrice;
    private String startDate;
    private String endDate;

}
