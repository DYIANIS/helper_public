package com.shtura.helper.service;

import com.shtura.helper.entity.UserInfo;
import com.shtura.helper.service.security.UsernameExistsException;
import com.shtura.helper.web.dto.UserInfoDto;

public interface UserInfoService {
    UserInfo inputUserInfo(UserInfoDto userInfoDto, String name) throws UsernameExistsException;
}
