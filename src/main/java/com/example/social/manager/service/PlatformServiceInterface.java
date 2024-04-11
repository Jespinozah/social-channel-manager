package com.example.social.manager.service;

import com.example.social.manager.domain.Group;
import com.example.social.manager.domain.Platform;
import com.example.social.manager.domain.UserLicense;

import java.util.List;

public interface PlatformServiceInterface {

    Integer create(String name);

    List<Platform> getPlatforms();

    Integer createGroup(String name, Integer platformId);

    List<Group> getGroups();

    Integer createUserLicense(String name, String role);

    List<UserLicense> getUserLicenses();
}
