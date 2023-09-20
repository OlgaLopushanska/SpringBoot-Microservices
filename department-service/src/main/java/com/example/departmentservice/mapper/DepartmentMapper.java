package com.example.departmentservice.mapper;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public static DepartmentDto mapToDepartmentDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto(department.getId(),
                department.getDepartmentName(), department.getDepartmentDescription(), department.getDepartmentCode());
        return departmentDto;
    }

}
