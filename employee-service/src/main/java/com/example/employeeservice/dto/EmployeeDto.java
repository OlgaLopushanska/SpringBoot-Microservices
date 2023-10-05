package com.example.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Schema(description = "Employee Model Information")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    @Schema(description = "Employee first name")
    private String firstName;
    @Schema(description = "Employee last name")
    private String lastName;
    @Schema(description = "Employee email")
    private String email;
    @Schema(description = "Employee`s department code")
    private String departmentCode;
    @Schema(description = "Employee`s organization code")
    private String organizationCode;
}
