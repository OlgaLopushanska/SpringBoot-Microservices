package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exception.EmployeeNotFoundException;
import com.example.employeeservice.mapper.EmployeeMapperMapstruct;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapperMapstruct.MAPPER.mapToEmployee(employeeDto);
      //  Employee employee = modelMapper.map(employeeDto, Employee.class);
//        Employee employee = new Employee(employeeDto.getId(),
//                employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail());
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto dto = EmployeeMapperMapstruct.MAPPER.mapToEmployeeDto(savedEmployee);
      //  EmployeeDto dto = modelMapper.map(savedEmployee, EmployeeDto.class);
//        EmployeeDto dto = new EmployeeDto(savedEmployee.getId(),
//                savedEmployee.getFirstName(), savedEmployee.getLastName(), savedEmployee.getEmail());
        return dto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee", "id", id));
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
       // EmployeeDto employeeDto = EmployeeMapperMapstruct.MAPPER.mapToEmployeeDto(employee);
//        EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getFirstName(),
//                employee.getLastName(), employee.getEmail());
        return employeeDto;
    }
}
