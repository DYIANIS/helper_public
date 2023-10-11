package com.shtura.helper.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shtura.helper.entity.UserInfo;

public interface UserInfoRepositories extends CrudRepository<UserInfo, String> {

}
