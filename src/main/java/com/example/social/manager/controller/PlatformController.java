package com.example.social.manager.controller;

import com.example.social.manager.controller.mapper.RestMapper;
import com.example.social.manager.controller.validation.AuthorizedRoles;
import com.example.social.manager.domain.Group;
import com.example.social.manager.domain.UserLicense;
import com.example.social.manager.service.PlatformServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

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

    private record UserLicenseCreateRequest(String name, String role) {
    }

    private record UserLicenseUpdateRequest(Integer userLicenseId, Integer userId, Integer groupId, String role) {
    }

    private record UserLicenseUpdateResponse(Integer id) {
    }

    private record UserLicenseCreateResponse(Integer id) {
    }

    public record Platform(Integer id, String name, Set<Group> groups) {
    }

    public record Group(Integer id, String name) {
    }

    public record UserLicense(Integer id, String name, String role, Group group, User user) {
    }

    public record User(Integer id, String email, String firstName, String role, String username) {
    }

    @Autowired
    private PlatformServiceInterface platformService;

    @Autowired
    private RestMapper mapper;

    @AuthorizedRoles({"SUPER_ADMIN"})
    @PostMapping(path = "")
    public @ResponseBody PlatformCreateResponse create(@RequestBody PlatformCreateRequest request) {
        return new PlatformCreateResponse(platformService.create(request.name));
    }

    @AuthorizedRoles({"SUPER_ADMIN"})
    @GetMapping(path = "")
    public @ResponseBody List<Platform> getPlatforms() {
        return platformService.getPlatforms().stream().map(p -> mapper.toPlatformRest(p)).toList();
    }

    @AuthorizedRoles({"SUPER_ADMIN"})
    @PostMapping(path = "/group")
    public @ResponseBody GroupCreateResponse createGroup(@RequestBody GroupCreateRequest request) {
        return new GroupCreateResponse(platformService.createGroup(request.name,
                request.platformId));
    }

    @AuthorizedRoles({"SUPER_ADMIN", "SIMPLE"})
    @GetMapping(path = "/group")
    public @ResponseBody List<Group> getGroups() {
        return platformService.getGroups().stream().map(p -> mapper.toGroupRest(p)).toList();
    }

    @AuthorizedRoles({"SUPER_ADMIN"})
    @PostMapping(path = "/group/user/license")
    public @ResponseBody UserLicenseCreateResponse createUserLicense(@RequestBody UserLicenseCreateRequest request) {
        return new UserLicenseCreateResponse(platformService.createUserLicense(request.name,
                request.role));
    }

    @AuthorizedRoles({"SUPER_ADMIN", "SIMPLE"})
    @GetMapping(path = "/group/user/license")
    public @ResponseBody List<UserLicense> getUserLicenses() {
        return platformService.getUserLicenses().stream().map(p -> mapper.toUserLicenseRest(p)).toList();
    }

    @AuthorizedRoles({"SUPER_ADMIN", "SIMPLE"})
    @PutMapping(path = "/group/user/license")
    public @ResponseBody UserLicenseUpdateResponse updateUserLicenses(@RequestBody UserLicenseUpdateRequest request) {
        return new UserLicenseUpdateResponse(platformService.updateUserLicense(request.userLicenseId,
                request.groupId, request.userId(), request.role));
    }
}
