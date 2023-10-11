package com.shtura.helper.repositories.helperdb;

import org.springframework.data.repository.CrudRepository;

import com.shtura.helper.entity.aptekajet.UserDB;

public interface UserDBRepository extends CrudRepository<UserDB, Integer> {
    UserDB findByName(String name);

}
