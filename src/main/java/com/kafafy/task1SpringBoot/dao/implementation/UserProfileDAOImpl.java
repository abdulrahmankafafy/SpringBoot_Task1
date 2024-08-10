package com.kafafy.task1SpringBoot.dao.implementation;

import com.kafafy.task1SpringBoot.dao.framework.UserProfileDAO;
import com.kafafy.task1SpringBoot.entity.UserProfile;
import com.kafafy.task1SpringBoot.utilities.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserProfileDAOImpl implements UserProfileDAO {

    @Override
    public List<UserProfile> getAllUserProfiles() {
        List<UserProfile> userProfiles = new ArrayList<>();
        String sql = "SELECT * FROM user_profile";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                UserProfile userProfile = new UserProfile();
                userProfile.setUserId(resultSet.getInt("USER_ID"));
                userProfile.setName(resultSet.getString("Name"));
                userProfile.setUserName(resultSet.getString("User_Name"));
                userProfile.setPassword(resultSet.getString("Password"));
                userProfile.setAddress(resultSet.getString("Address"));
                userProfile.setNationalId(resultSet.getString("National_Id"));
                userProfile.setPhone(resultSet.getString("Phone"));
                userProfile.setDepartmentId(resultSet.getInt("Department_Id"));
                userProfile.setSalary(resultSet.getDouble("Salary"));
                userProfile.setBirthDate(resultSet.getDate("birth_date").toLocalDate());

                userProfiles.add(userProfile);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userProfiles;
    }

    @Override
    public UserProfile getUserProfileById(int userId) {
        UserProfile userProfile = null;
        String sql = "SELECT * FROM user_profile WHERE USER_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userProfile = new UserProfile();
                userProfile.setUserId(resultSet.getInt("USER_ID"));
                userProfile.setName(resultSet.getString("Name"));
                userProfile.setUserName(resultSet.getString("User_Name"));
                userProfile.setPassword(resultSet.getString("Password"));
                userProfile.setAddress(resultSet.getString("Address"));
                userProfile.setNationalId(resultSet.getString("National_Id"));
                userProfile.setPhone(resultSet.getString("Phone"));
                userProfile.setDepartmentId(resultSet.getInt("Department_Id"));
                userProfile.setSalary(resultSet.getDouble("Salary"));
                userProfile.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userProfile;
    }

    @Override
    public UserProfile getUserProfileByNationalId(String nationalId) {
        UserProfile userProfile = null;
        String sql = "SELECT * FROM user_profile WHERE National_Id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nationalId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userProfile = new UserProfile();
                userProfile.setUserId(resultSet.getInt("USER_ID"));
                userProfile.setName(resultSet.getString("Name"));
                userProfile.setUserName(resultSet.getString("User_Name"));
                userProfile.setPassword(resultSet.getString("Password"));
                userProfile.setAddress(resultSet.getString("Address"));
                userProfile.setNationalId(resultSet.getString("National_Id"));
                userProfile.setPhone(resultSet.getString("Phone"));
                userProfile.setDepartmentId(resultSet.getInt("Department_Id"));
                userProfile.setSalary(resultSet.getDouble("Salary"));
                userProfile.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userProfile;
    }

    @Override
    public boolean addUserProfile(UserProfile userProfile) {
        String sql = "INSERT INTO user_profile (Name, User_Name, Password, Address, National_Id, Phone, Department_Id, Salary, birth_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, userProfile.getName());
            preparedStatement.setString(2, userProfile.getUserName());
            preparedStatement.setString(3, userProfile.getPassword());
            preparedStatement.setString(4, userProfile.getAddress());
            preparedStatement.setString(5, userProfile.getNationalId());
            preparedStatement.setString(6, userProfile.getPhone());
            preparedStatement.setInt(7, userProfile.getDepartmentId());
            preparedStatement.setDouble(8, userProfile.getSalary());
            preparedStatement.setDate(9, Date.valueOf(userProfile.getBirthDate()));

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUserProfile(UserProfile userProfile) {
        String sql = "UPDATE user_profile SET Name = ?, User_Name = ?, Password = ?, Address = ?, National_Id = ?, Phone = ?, Department_Id = ?, Salary = ?, birth_date = ? WHERE USER_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, userProfile.getName());
            preparedStatement.setString(2, userProfile.getUserName());
            preparedStatement.setString(3, userProfile.getPassword());
            preparedStatement.setString(4, userProfile.getAddress());
            preparedStatement.setString(5, userProfile.getNationalId());
            preparedStatement.setString(6, userProfile.getPhone());
            preparedStatement.setInt(7, userProfile.getDepartmentId());
            preparedStatement.setDouble(8, userProfile.getSalary());
            preparedStatement.setDate(9, Date.valueOf(userProfile.getBirthDate()));
            preparedStatement.setInt(10, userProfile.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUserProfile(int userId) {
        String sql = "DELETE FROM user_profile WHERE USER_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
