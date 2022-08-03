package com.kimcompany.jangbogbackendver2.Employee.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class TryInsertDto {

    @NotBlank(message = "매장을 선택해 주세요")
    private String storeId;
    @NotBlank(message = "초대할 직원을 선택해 주세요")
    private String userId;

}
