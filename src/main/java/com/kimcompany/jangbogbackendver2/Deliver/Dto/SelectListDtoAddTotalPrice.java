package com.kimcompany.jangbogbackendver2.Deliver.Dto;

import com.kimcompany.jangbogbackendver2.Util.UtilService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
@Data
public class SelectListDtoAddTotalPrice {
    Page<SelectListDto> selectListDtos;
    String totalPrice;

    public void setTotalPrice( List<SelectListDto>selectListDtos){
        int total = 0;
        for(SelectListDto s:selectListDtos){
            total += s.getPrice();
        }
        this.totalPrice= UtilService.confirmPrice(total);
    }
}
