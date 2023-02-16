package com.bosch.Exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String errorMessage;
//    private LocalDateTime time;
}