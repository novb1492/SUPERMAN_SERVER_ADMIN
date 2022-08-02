package com.kimcompany.jangbogbackendver2.Employee.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class TryInsertDto {

    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @NotBlank(message = "전화번호를 입력해 주세요")
    private String phone;
    @NotBlank(message = "이메일을 입력해 주세요")
    private String email;
    @NotBlank(message = "매장을 선택해 주세요")
    private String storeId;
    @NotBlank(message = "초대할 직원을 선택해 주세요")
    private String userId;
    @NotBlank(message = "직급을 선택해 주세요")
    private String role;
}
