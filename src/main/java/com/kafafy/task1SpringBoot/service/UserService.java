package com.kafafy.task1SpringBoot.service;

import com.kafafy.task1SpringBoot.dao.framework.UserProfileDAO;
import com.kafafy.task1SpringBoot.entity.UserProfile;
import com.kafafy.task1SpringBoot.model.UserError;
import com.kafafy.task1SpringBoot.model.UserModel;
import com.kafafy.task1SpringBoot.utilities.NationalIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserProfileDAO userProfileDAO;

    @Autowired
    public UserService(UserProfileDAO userProfileDAO) {
        this.userProfileDAO = userProfileDAO;
    }

    public List<UserModel> getAllUsers() {
        return userProfileDAO.getAllUserProfiles().stream()
                .map(this::convertToUserModel)
                .collect(Collectors.toList());
    }

    public UserModel getUserById(int id) {
        UserProfile userProfile = userProfileDAO.getUserProfileById(id);
        return userProfile != null ? convertToUserModel(userProfile) : null;
    }

    public UserError addUser(UserModel userModel) {
        if (userProfileDAO.getUserProfileByNationalId(userModel.getNationalId()) != null) {
            return new UserError("National ID must be unique. This ID is already in use.", 400, false);
        }

        LocalDate birthDate = NationalIdUtils.extractBirthDateFromNationalId(userModel.getNationalId());

        UserProfile userProfile = convertToUserProfile(userModel);
        userProfile.setBirthDate(birthDate);

        boolean success = userProfileDAO.addUserProfile(userProfile);
        if (success) {
            return new UserError("User added successfully.", 200, true);
        } else {
            return new UserError("Error adding user.", 500, false);
        }
    }

    public UserError updateUser(int id, UserModel userModel) {
        UserProfile existingUser = userProfileDAO.getUserProfileById(id);
        if (existingUser == null) {
            return new UserError("User not found.", 404, false);
        }

        if (!existingUser.getNationalId().equals(userModel.getNationalId()) &&
                userProfileDAO.getUserProfileByNationalId(userModel.getNationalId()) != null) {
            return new UserError("National ID must be unique. This ID is already in use.", 400, false);
        }

        LocalDate birthDate = NationalIdUtils.extractBirthDateFromNationalId(userModel.getNationalId());

        UserProfile userProfile = convertToUserProfile(userModel);
        userProfile.setUserId(id);
        userProfile.setBirthDate(birthDate);

        boolean success = userProfileDAO.updateUserProfile(userProfile);
        if (success) {
            return new UserError("User updated successfully.", 200, true);
        } else {
            return new UserError("Error updating user.", 500, false);
        }
    }

    public UserError deleteUser(int id) {
        UserProfile existingUser = userProfileDAO.getUserProfileById(id);
        if (existingUser == null) {
            return new UserError("User not found.", 404, false);
        }

        boolean success = userProfileDAO.deleteUserProfile(id);
        if (success) {
            return new UserError("User deleted successfully.", 200, true);
        } else {
            return new UserError("Error deleting user.", 500, false);
        }
    }

    private UserProfile convertToUserProfile(UserModel userModel) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userModel.getUserId());
        userProfile.setName(userModel.getName());
        userProfile.setUserName(userModel.getUserName());
        userProfile.setPassword(userModel.getPassword());
        userProfile.setAddress(userModel.getAddress());
        userProfile.setNationalId(userModel.getNationalId());
        userProfile.setPhone(userModel.getPhone());
        userProfile.setDepartmentId(userModel.getDepartmentId());
        userProfile.setSalary(userModel.getSalary());
        return userProfile;
    }

    private UserModel convertToUserModel(UserProfile userProfile) {
        UserModel userModel = new UserModel();
        userModel.setUserId(userProfile.getUserId());
        userModel.setName(userProfile.getName());
        userModel.setUserName(userProfile.getUserName());
        userModel.setPassword(userProfile.getPassword());
        userModel.setAddress(userProfile.getAddress());
        userModel.setNationalId(userProfile.getNationalId());
        userModel.setPhone(userProfile.getPhone());
        userModel.setDepartmentId(userProfile.getDepartmentId());
        userModel.setSalary(userProfile.getSalary());
        userModel.setBirthDate(userProfile.getBirthDate());
        userModel.setAge(userProfile.getAge());
        userModel.setDepartmentName(userProfile.getDepartmentName());
        return userModel;
    }
}
