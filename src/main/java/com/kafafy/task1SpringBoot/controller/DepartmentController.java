package com.kafafy.task1SpringBoot.controller;

import com.kafafy.task1SpringBoot.model.DepartmentModel;
import com.kafafy.task1SpringBoot.model.UserError;
import com.kafafy.task1SpringBoot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentModel>> getAllDepartments() {
        List<DepartmentModel> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentModel> getDepartmentById(@PathVariable int id) {
        DepartmentModel department = departmentService.getDepartmentById(id);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public ResponseEntity<UserError> addDepartment(@RequestBody DepartmentModel departmentModel) {
        UserError result = departmentService.addDepartment(departmentModel);
        if (!result.isSuccess()) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserError> updateDepartment(@PathVariable int id, @RequestBody DepartmentModel departmentModel) {
        UserError result = departmentService.updateDepartment(id, departmentModel);
        if (!result.isSuccess()) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserError> deleteDepartment(@PathVariable int id) {
        UserError result = departmentService.deleteDepartment(id);
        if (!result.isSuccess()) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }
}
