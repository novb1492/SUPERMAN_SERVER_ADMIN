package com.kimcompany.jangbogbackendver2.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomException extends Throwable {
    private ErrorCode errorCode;
}
