package com.example.social.manager.service;

import com.example.social.manager.domain.Group;
import com.example.social.manager.domain.Platform;
import com.example.social.manager.domain.UserLicense;
import com.example.social.manager.repository.GroupRepository;
import com.example.social.manager.repository.PlatformRepository;
import com.example.social.manager.repository.UserLicenseRepository;
import com.example.social.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformServiceService implements PlatformServiceInterface {

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLicenseRepository userLicenseRepository;
    @Override
    public Integer create(String name) {
        var platform = new Platform();
        platform.setName(name);
        return platformRepository.save(platform).getId();
    }

    @Override
    public Integer createGroup(String name, Integer platformId) {
        var group = new Group();
        var platform = platformRepository.findById(platformId).orElseThrow();
        group.setName(name);
        group.setPlatform(platform);
        return groupRepository.save(group).getId();
    }

    @Override
    public Integer createUserLicense(String name, Integer userId, Integer groupId) {
        var userLicense = new UserLicense();
        var user = userRepository.findById(userId).orElseThrow();
        var group = groupRepository.findById(groupId).orElseThrow();
        userLicense.setName(name);
        userLicense.setUser(user);
        userLicense.setGroup(group);
        return userLicenseRepository.save(userLicense).getId();
    }
}
