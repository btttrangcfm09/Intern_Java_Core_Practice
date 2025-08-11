package dev.cat.controller;

import dev.cat.dto.EmployeeDto;
import dev.cat.dto.NewEmployeeDto;
import dev.cat.entity.Employee;
import dev.cat.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employee Module", description = "Operations with employee data")
@RestController
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }

    @Tag(name = "get", description = "GET methods of Employee APIs")
    @GetMapping("/employees")
    public List<EmployeeDto> findAllEmployees() {
        return employeeService.findAll();
    }

    @Tag(name = "get", description = "Retrieve one employee")
    @GetMapping("/employees/{employeeId}")
    public EmployeeDto getEmployee(
        @Parameter(
            description = "ID of employee to be retrieved",
            required = true)
                                   @PathVariable int employeeId) {

        return employeeService.findById(employeeId);
    }

    @PostMapping("/employees")
    public EmployeeDto addEmployee(@RequestBody NewEmployeeDto employee) {
        return employeeService.save(employee);
    }

    @Operation(summary = "Update an employee",
            description = "Update an existing employee. The response is updated Employee object with id, first name, and last name.")
    @PutMapping("/employees")
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employee) {
        return employeeService.update(employee);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content)})
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteById(employeeId);
        return "Deleted employee with id: " + employeeId;
    }
}
