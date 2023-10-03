package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exception.EmployeeNotFoundException;
import com.example.employeeservice.mapper.EmployeeMapperMapstruct;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.APIClient;
import com.example.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private RestTemplate restTemplate;
    private WebClient webClient;
    private APIClient apiClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        //   Employee employee = EmployeeMapperMapstruct.MAPPER.mapToEmployee(employeeDto);
        Employee employee = modelMapper.map(employeeDto, Employee.class);
//        Employee employee = new Employee(employeeDto.getId(),
//                employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail());
        Employee savedEmployee = employeeRepository.save(employee);
        //EmployeeDto dto = EmployeeMapperMapstruct.MAPPER.mapToEmployeeDto(savedEmployee);
        EmployeeDto dto = modelMapper.map(savedEmployee, EmployeeDto.class);
//        EmployeeDto dto = new EmployeeDto(savedEmployee.getId(),
//                savedEmployee.getFirstName(), savedEmployee.getLastName(), savedEmployee.getEmail());
        return dto;
    }

    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDafaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long id) {
        LOGGER.info("inside getEmployeeById method");
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee", "id", id));
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8087/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
      //  DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        // EmployeeDto employeeDto = EmployeeMapperMapstruct.MAPPER.mapToEmployeeDto(employee);
//        EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getFirstName(),
//                employee.getLastName(), employee.getEmail());
        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId, Exception e) {
        LOGGER.info("inside getDefaultDepartment method");
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException("Employee", "id", employeeId));
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D");
        departmentDto.setDepartmentCode("1223");
        departmentDto.setDepartmentDescription("Research and devalopment");
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(), employee.getFirstName(), employee.getLastName(),
                employee.getEmail(), employee.getDepartmentCode());
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        return apiResponseDto;
    }
}
