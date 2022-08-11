package com.kimcompany.jangbogbackendver2.Store.Dto;

import com.kimcompany.jangbogbackendver2.Common.AddressColumn;
import com.kimcompany.jangbogbackendver2.Common.CommonColumn;
import com.kimcompany.jangbogbackendver2.Member.Model.MemberEntity;
import com.kimcompany.jangbogbackendver2.Store.Model.StoreEntity;
import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class TryInsertDto {

    @NotBlank(message = "매장 썸네일을 업로드해주세요")
    private String thumbnail;
    @NotBlank(message = "매장 오픈 시간을 선택해주세요")
    private String openTime;
    @NotBlank(message = "매장 마감 시간을 선택해주세요")
    private String closeTime;
    @NotBlank(message = "우편번호를 입력해주세요")
    private String postcode;
    @NotBlank(message = "주소를 입력해주세요")
    private String address;
    @NotBlank(message = "매장이름을 입력해주세요")
    private String name;

    private String text;

    @NotBlank(message = "매장상세주소를 입력해주세요")
    private String detailAddress;


    @Min(value = 1,message = "최소 배달금액을 입력해주세요")
    private int minPrice;

    @NotBlank(message = "최대 배달반경을 입력해주세요")
    private String radius;

    @NotBlank(message = "매장 전화번호를 입력해주세요")
    private String tel;

    public static StoreEntity dtoToEntity(TryInsertDto tryInsertDto){
        return StoreEntity.builder().memberEntity(MemberEntity.builder().id(UtilService.getPrincipalDetails().getMemberEntity().getId()).build())
                .addressColumn(AddressColumn.set(tryInsertDto.getPostcode(), tryInsertDto.getAddress(), tryInsertDto.getDetailAddress()))
                .closeTime(tryInsertDto.getCloseTime()).openTime(tryInsertDto.getOpenTime()).maxDeliverRadius(Double.parseDouble(tryInsertDto.getRadius()))
                .name(tryInsertDto.getName()).tel(tryInsertDto.getTel()).text(tryInsertDto.getText()).commonColumn(CommonColumn.set(1))
                .minOrderPrice(tryInsertDto.getMinPrice()).thumbNail(tryInsertDto.getThumbnail()).build();
    }

}
