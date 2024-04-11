package com.example.social.manager.controller;

import com.example.social.manager.controller.validation.AuthorizedRoles;
import com.example.social.manager.service.PlatformServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/platform")
public class PlatformController {

    private record PlatformCreateRequest(String name) {
    }

    private record PlatformCreateResponse(Integer id) {
    }

    private record GroupCreateRequest(String name, Integer platformId) {
    }

    private record GroupCreateResponse(Integer id) {
    }

    private record UserLicenseCreateRequest(String name, Integer userId, Integer groupId) {
    }

    private record UserLicenseCreateResponse(Integer id) {
    }

    @Autowired
    private PlatformServiceInterface platformService;

    @AuthorizedRoles({"SUPER_ADMIN"})
    @PostMapping(path = "")
    public @ResponseBody PlatformCreateResponse create(@RequestBody PlatformCreateRequest platformCreateRequest) {
        return new PlatformCreateResponse(platformService.create(platformCreateRequest.name));
    }

    @AuthorizedRoles({"SUPER_ADMIN"})
    @PostMapping(path = "/group")
    public @ResponseBody GroupCreateResponse createGroup(@RequestBody GroupCreateRequest groupCreateRequest) {
        return new GroupCreateResponse(platformService.createGroup(groupCreateRequest.name,
                groupCreateRequest.platformId));
    }

    @AuthorizedRoles({"SUPER_ADMIN"})
    @PostMapping(path = "/group/user/license")
    public @ResponseBody UserLicenseCreateResponse createUserLicense(@RequestBody UserLicenseCreateRequest userLicenseCreateRequest) {
        return new UserLicenseCreateResponse(platformService.createUserLicense(userLicenseCreateRequest.name,
                userLicenseCreateRequest.userId,
                userLicenseCreateRequest.groupId));
    }

}
