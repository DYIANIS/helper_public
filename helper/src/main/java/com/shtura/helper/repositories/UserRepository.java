package com.shtura.helper.repositories;

import com.shtura.helper.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);
    
    /*@Modifying
    @Query("UPDATE Space c SET c.owner = :name WHERE c.id = :id")
    Integer setNameForId(@Param("name") String name, @Param("id")*/
    
    /*@Query("from User u where u.login = :login ORDER BY u.id ASC")
    List<User> findByLoginForEdit(@Param("login") String login);*/
    
}
