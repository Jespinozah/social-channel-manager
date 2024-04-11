package com.example.social.manager.service;

public interface PlatformServiceInterface {

    Integer create(String name);

    Integer createGroup(String name, Integer platformId);

    Integer createUserLicense(String name, Integer userId, Integer groupId);
}
