package com.shtura.helper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtura.helper.entity.UserInfo;
import com.shtura.helper.repositories.UserInfoRepositories;
import com.shtura.helper.service.security.UsernameExistsException;
import com.shtura.helper.web.dto.UserInfoDto;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepositories userInfoRepositories;

    public UserInfo inputUserInfo(UserInfoDto userInfoDto, String name) throws UsernameExistsException {
        UserInfo userInfo = new UserInfo();
        
        userInfo.setId(name);
        userInfo.setName(userInfoDto.getName());
        userInfo.setSurname(userInfoDto.getSurname());
        userInfo.setPatronymic(userInfoDto.getPatronymic());

        this.userInfoRepositories.save(userInfo);
        return userInfo;
    }

    public boolean userInfoExist(String login) {
        UserInfo userInfo = this.userInfoRepositories.findById(login).get();
        if (userInfo != null) {
            return true;
        }
        return false;
    }
}
