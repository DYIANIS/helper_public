package com.shtura.helper.repositories.helperdb;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shtura.helper.entity.helperdb.ConnectionIPAdress;
import com.shtura.helper.entity.helperdb.PosVersion;

public interface PosVersionRepository extends CrudRepository<PosVersion, Integer> {
    @Query("from PosVersion pV where pV.inventoryHostname = :inventoryHostname ORDER BY pV.id ASC")
    List<PosVersion> findByInventoryHostname(@Param("inventoryHostname") String inventoryHostname);

}
