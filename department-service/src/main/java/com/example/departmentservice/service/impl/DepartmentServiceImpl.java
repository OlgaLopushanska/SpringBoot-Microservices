package com.example.departmentservice.service.impl;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.mapper.DepartmentMapper;
import com.example.departmentservice.mapper.DepartmentMapperWithMapstruct;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto dto) {
        Department department = DepartmentMapperWithMapstruct.MAPPER.mapToDepartment(dto);
       // Department department = modelMapper.map(dto, Department.class);
//        Department department = new Department(dto.getId(),
//                dto.getDepartmentName(), dto.getDepartmentDescription(), dto.getDepartmentCode());
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto departmentDto = DepartmentMapperWithMapstruct.MAPPER.mapToDepartmentDto(savedDepartment);
     //   DepartmentDto departmentDto = modelMapper.map(savedDepartment, DepartmentDto.class);
//        DepartmentDto departmentDto = new DepartmentDto(savedDepartment.getId(),
//                savedDepartment.getDepartmentName(), savedDepartment.getDepartmentDescription(),
//                savedDepartment.getDepartmentCode());
        return departmentDto;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departmentList = departmentRepository.findAll();
        List<DepartmentDto> departmentDtoList = departmentList.stream()
              //  .map(department-> DepartmentMapper.mapToDepartmentDto(department))
                .map(DepartmentMapperWithMapstruct.MAPPER::mapToDepartmentDto)
                //.map(department -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
        return departmentDtoList;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code);
        DepartmentDto departmentDto = DepartmentMapperWithMapstruct.MAPPER.mapToDepartmentDto(department);
       // DepartmentDto  departmentDto = modelMapper.map(department, DepartmentDto.class);
        //DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);
        return departmentDto;
    }
}
