package com.kimcompany.jangbogbackendver2.Order.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class SelectListDto {

    private long id;
    private String orderOwnName;
    private LocalDateTime orderDate;
    private String destinationPostCode;
    private String destinationAddr;
    private String destinationDetailAddr;

}
