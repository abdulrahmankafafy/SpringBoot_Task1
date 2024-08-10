package com.kafafy.task1SpringBoot.dao.framework;

import com.kafafy.task1SpringBoot.entity.UserProfile;
import java.util.List;

public interface UserProfileDAO {

    List<UserProfile> getAllUserProfiles();
    UserProfile getUserProfileByNationalId(String nationalId);
    UserProfile getUserProfileById(int userId);

    boolean addUserProfile(UserProfile userProfile);
    boolean updateUserProfile(UserProfile userProfile);
    boolean deleteUserProfile(int userId);
}
