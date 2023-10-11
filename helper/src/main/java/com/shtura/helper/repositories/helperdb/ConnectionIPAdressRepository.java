package com.shtura.helper.repositories.helperdb;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.shtura.helper.entity.helperdb.ConnectionIPAdress;

public interface ConnectionIPAdressRepository extends CrudRepository<ConnectionIPAdress, Integer> {
    @Query("from ConnectionIPAdress cIPA where cIPA.connectionNameOrganization.id = :connectionNameOrganizationID ORDER BY cIPA.id ASC")
    List<ConnectionIPAdress> findByConnectionIPAdressID(@Param("connectionNameOrganizationID") Integer connectionNameOrganizationID);

    /*@Modifying
    @Transactional
    @Query("DELETE FROM ConnectionIPAdress cIPA WHERE cIPA.id = :connectionNameOrganizationID")
    void delete(@Param("connectionNameOrganizationID") Integer id);*/
}
