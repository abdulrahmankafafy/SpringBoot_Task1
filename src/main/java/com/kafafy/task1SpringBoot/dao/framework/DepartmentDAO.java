package com.kafafy.task1SpringBoot.dao.framework;

import com.kafafy.task1SpringBoot.entity.Department;

import java.util.List;

public interface DepartmentDAO {

    List<Department> getAllDepartments();

    Department getDepartmentById(int departmentId);

    void addDepartment(Department department);

    void updateDepartment(Department department);

    void deleteDepartment(int departmentId);
}
