package com.shtura.helper.repositories.helperdb;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shtura.helper.entity.helperdb.ConnectionNameOrganization;

public interface ConnectionNameOrganizationRepository extends CrudRepository<ConnectionNameOrganization, Integer> {
    @Query("select cNO.name from ConnectionNameOrganization cNO")
    List<String> findAllNames();
    
    @Query("from ConnectionNameOrganization cNO where cNO.name = :name")
    List<ConnectionNameOrganization> findByName(@Param("name") String name);
}
