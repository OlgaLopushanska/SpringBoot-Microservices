package com.example.departmentservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private LocalDateTime time;
    private String message;
    private String path;
    private String errorCode;

}
