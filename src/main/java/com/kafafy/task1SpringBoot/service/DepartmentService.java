package com.kafafy.task1SpringBoot.service;

import com.kafafy.task1SpringBoot.dao.framework.DepartmentDAO;
import com.kafafy.task1SpringBoot.model.DepartmentModel;
import com.kafafy.task1SpringBoot.model.UserError;
import com.kafafy.task1SpringBoot.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentDAO departmentDAO;

    @Autowired
    public DepartmentService(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public List<DepartmentModel> getAllDepartments() {
        return departmentDAO.getAllDepartments().stream()
                .map(this::convertToDepartmentModel)
                .collect(Collectors.toList());
    }

    public DepartmentModel getDepartmentById(int id) {
        Department department = departmentDAO.getDepartmentById(id);
        return department != null ? convertToDepartmentModel(department) : null;
    }

    public UserError addDepartment(DepartmentModel departmentModel) {
        Department department = convertToDepartmentEntity(departmentModel);
        departmentDAO.addDepartment(department);
        return new UserError("Department added successfully.", 200, true);
    }

    public UserError updateDepartment(int id, DepartmentModel departmentModel) {
        Department existingDepartment = departmentDAO.getDepartmentById(id);
        if (existingDepartment == null) {
            return new UserError("Department not found.", 404, false);
        }

        Department department = convertToDepartmentEntity(departmentModel);
        department.setDepartmentId(id);
        departmentDAO.updateDepartment(department);
        return new UserError("Department updated successfully.", 200, true);
    }

    public UserError deleteDepartment(int id) {
        Department existingDepartment = departmentDAO.getDepartmentById(id);
        if (existingDepartment == null) {
            return new UserError("Department not found.", 404, false);
        }
        departmentDAO.deleteDepartment(id);
        return new UserError("Department deleted successfully.", 200, true);
    }

    private Department convertToDepartmentEntity(DepartmentModel departmentModel) {
        Department department = new Department();
        department.setDepartmentId(departmentModel.getDepartmentId());
        department.setUserId(departmentModel.getUserId());
        department.setDepartmentName(departmentModel.getDepartmentName());
        return department;
    }

    private DepartmentModel convertToDepartmentModel(Department department) {
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setDepartmentId(department.getDepartmentId());
        departmentModel.setUserId(department.getUserId());
        departmentModel.setDepartmentName(department.getDepartmentName());
        return departmentModel;
    }
}
