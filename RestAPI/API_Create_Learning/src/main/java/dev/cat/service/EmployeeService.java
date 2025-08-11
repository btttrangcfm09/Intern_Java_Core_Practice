package dev.cat.service;

import dev.cat.dto.EmployeeDto;
import dev.cat.dto.EmployeeMapper;
import dev.cat.dto.NewEmployeeDto;
import dev.cat.entity.Employee;
import dev.cat.exception.NotFoundException;
import dev.cat.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = employeeMapper;
    }

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(mapper::mapToEmployeeDto)
                .toList();
    }

    public EmployeeDto findById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new NotFoundException("Employee not found with id: " + id);
        }
        return mapper.mapToEmployeeDto(employee.get());
    }

    public EmployeeDto save(NewEmployeeDto employeeDto) {
        Employee employee = mapper.mapToEmployee(employeeDto);
        return mapper.mapToEmployeeDto(employeeRepository.save(employee));
    }

    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee employee = mapper.mapToEmployee(employeeDto);
        return mapper.mapToEmployeeDto(employeeRepository.save(employee));
    }

    public void deleteById(int id) {
        if (!employeeRepository.existsById(id)) {
            throw new NotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

}
