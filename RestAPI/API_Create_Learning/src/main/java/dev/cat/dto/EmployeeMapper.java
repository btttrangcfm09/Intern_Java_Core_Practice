package dev.cat.dto;

import dev.cat.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto mapToEmployeeDto(Employee employee);

    Employee mapToEmployee(EmployeeDto employeeDto);

    Employee mapToEmployee(NewEmployeeDto employeeDto);

}

