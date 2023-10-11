package com.shtura.helper.service.security;

import com.shtura.helper.entity.User;
import com.shtura.helper.web.dto.UserDto;

public interface UserRegistrationService {
    User registerNewUser(UserDto accountDto) throws UsernameExistsException;
}
