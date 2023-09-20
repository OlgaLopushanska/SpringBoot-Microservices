package com.example.departmentservice.service;

import com.example.departmentservice.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto dto);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto getDepartmentByCode(String code);
}
