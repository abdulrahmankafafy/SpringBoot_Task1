package com.kafafy.task1SpringBoot.dao.implementation;

import com.kafafy.task1SpringBoot.dao.framework.DepartmentDAO;
import com.kafafy.task1SpringBoot.entity.Department;
import com.kafafy.task1SpringBoot.utilities.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM department";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getInt("DEPARTMENT_ID"));
                department.setUserId(resultSet.getInt("USER_ID"));
                department.setDepartmentName(resultSet.getString("DEPARTMENT_NAME"));

                departments.add(department);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departments;
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        Department department = null;
        String sql = "SELECT * FROM department WHERE DEPARTMENT_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, departmentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                department = new Department();
                department.setDepartmentId(resultSet.getInt("DEPARTMENT_ID"));
                department.setUserId(resultSet.getInt("USER_ID"));
                department.setDepartmentName(resultSet.getString("DEPARTMENT_NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return department;
    }

    @Override
    public void addDepartment(Department department) {
        String sql = "INSERT INTO department (USER_ID, DEPARTMENT_NAME) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, department.getUserId());
            preparedStatement.setString(2, department.getDepartmentName());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                department.setDepartmentId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDepartment(Department department) {
        String sql = "UPDATE department SET USER_ID = ?, DEPARTMENT_NAME = ? WHERE DEPARTMENT_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, department.getUserId());
            preparedStatement.setString(2, department.getDepartmentName());
            preparedStatement.setInt(3, department.getDepartmentId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDepartment(int departmentId) {
        String sql = "DELETE FROM department WHERE DEPARTMENT_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, departmentId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
