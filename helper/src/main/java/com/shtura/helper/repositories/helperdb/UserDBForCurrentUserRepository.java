package com.shtura.helper.repositories.helperdb;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shtura.helper.entity.helperdb.UserDBForCurrentUser;

@Repository
public interface UserDBForCurrentUserRepository extends CrudRepository<UserDBForCurrentUser, Integer> {
    @Query("from UserDBForCurrentUser u where u.username = :username")
    UserDBForCurrentUser findByUsername(@Param("username") String username);
    
}
