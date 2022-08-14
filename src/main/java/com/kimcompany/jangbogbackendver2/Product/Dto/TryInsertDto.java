package com.kimcompany.jangbogbackendver2.Product.Dto;

import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Store.Model.StoreEntity;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import com.kimcompany.jangbogbackendver2.Product.Model.ProductEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.kimcompany.jangbogbackendver2.Text.BasicText.trueStateNum;

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

    @NotBlank(message = "금액을 입력해주세요")
    private String  price;

    @NotBlank(message = "상품 이름을 적어주세요")
    private String name;

    @NotBlank(message = "매장정보를 유실했습니다")
    private String id;

    /**
     * 상품의 이벤트 내용 null가능
     */
    private String eventPrice;
    private String startDate;
    private String endDate;

    public static ProductEntity dtoToEntity(TryInsertDto tryInsertDto){
        return ProductEntity.builder().category(tryInsertDto.getCategory()).commonColumn(CommonColumn.set(trueStateNum))
                .introduce(tryInsertDto.getIntroduce()).name(tryInsertDto.getName()).origin(tryInsertDto.getOrigin())
                .price(tryInsertDto.getPrice()).productImgPath(tryInsertDto.getProductImgPath()).memberEntity(MemberEntity.builder().id(UtilService.getLoginUserId())
                        .build()).storeEntity(StoreEntity.builder().id(Long.parseLong(tryInsertDto.getId())).build()).build();
    }
//    public static ProductEventEntity dtoToEntityEvent(TryInsertDto tryInsertDto,long productId){
//        return ProductEventEntity.builder().productEntity(ProductEntity.builder().id(productId).build())
//                .endDate(t).build();
//    }

}
