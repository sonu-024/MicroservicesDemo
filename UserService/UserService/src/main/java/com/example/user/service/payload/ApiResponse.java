package com.example.user.service.payload;

import lombok.*;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ApiResponse {
    private String message;
    private Boolean success;
    private HttpStatus status;

}
