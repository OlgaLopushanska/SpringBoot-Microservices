package com.example.organizationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Schema(description = "Organization Model Information")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
    private Long id;
    @Schema(description = "Organization Name")
    private String organizationName;
    @Schema(description = "Organization Description")
    private String organizationDescription;
    @Schema(description = "Organization Code")
    private String organizationCode;
    @Schema(description = "Organization Created Date")
    private LocalDateTime createdDate;
}
