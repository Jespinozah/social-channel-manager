package com.example.social.manager.controller.mapper;

import com.example.social.manager.controller.PlatformController;
import com.example.social.manager.controller.UserController;
import com.example.social.manager.domain.Group;
import com.example.social.manager.domain.Platform;
import com.example.social.manager.domain.User;
import com.example.social.manager.domain.UserLicense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestMapper {
    PlatformController.Platform toPlatformRest(Platform platform);

    PlatformController.Group toGroupRest(Group group);

    PlatformController.UserLicense toUserLicenseRest(UserLicense userLicense);

    PlatformController.User toUserRest(User user);
    UserController.User toUserControllerRest(User user);

}
