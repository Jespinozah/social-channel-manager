package com.example.social.manager.service;

import com.example.social.manager.domain.Channel;
import com.example.social.manager.domain.Group;
import com.example.social.manager.domain.Platform;
import com.example.social.manager.domain.UserLicense;
import com.example.social.manager.repository.ChannelRepository;
import com.example.social.manager.repository.GroupRepository;
import com.example.social.manager.repository.PlatformRepository;
import com.example.social.manager.repository.UserLicenseRepository;
import com.example.social.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public Integer create(String name) {
        var platform = new Platform();
        platform.setName(name);
        return platformRepository.save(platform).getId();
    }

    @Override
    public List<Platform> getPlatforms() {
        return platformRepository.findAll();
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
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Integer createUserLicense(String name, String role) {
        var userLicense = new UserLicense();
        userLicense.setName(name);
        userLicense.setRole(role);
        return userLicenseRepository.save(userLicense).getId();
    }

    @Override
    public List<UserLicense> getUserLicenses() {
        return userLicenseRepository.findAll();
    }

    @Override
    public Integer updateUserLicense(Integer userLicenseId, Integer groupId, Integer userId, String role) {
        var userLicense = userLicenseRepository.findById(userLicenseId).orElseThrow();
        var group = groupRepository.findById(groupId).orElseThrow();
        var user = userRepository.findById(userId).orElseThrow();
        userLicense.setUser(user);
        userLicense.setGroup(group);
        userLicense.setRole(role);

        return userLicenseRepository.save(userLicense).getId();
    }

    @Override
    public Integer createChannel(String name, String type, Integer userLicenseId) {
        var channel = new Channel();
        var userLicense = userLicenseRepository.findById(userLicenseId).orElseThrow();
        channel.setName(name);
        channel.setType(type);
        channel.setUserLicense(userLicense);
        return channelRepository.save(channel).getId() ;

    }

    @Override
    public void deleteChannel(Integer id) {

        channelRepository.deleteById(id);
    }
}
