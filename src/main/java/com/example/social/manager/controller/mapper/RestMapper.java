package com.example.social.manager.controller.mapper;

import com.example.social.manager.controller.PlatformController;
import com.example.social.manager.domain.Group;
import com.example.social.manager.domain.Platform;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestMapper {
    PlatformController.Platform toPlatformRest(Platform platform);

    PlatformController.Group toGroupRest(Group group);


}
