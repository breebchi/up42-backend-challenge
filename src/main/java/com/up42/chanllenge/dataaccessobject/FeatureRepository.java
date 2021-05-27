package com.up42.chanllenge.dataaccessobject;

import com.up42.chanllenge.domainobject.FeatureDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeatureRepository extends JpaRepository<FeatureDO, String>
{
    @Override
    //@Query("select f.id, f.timestamp, f.beginViewingDate, f.endViewingDate, f.missionName from FeatureDO f where f.id = ?1")
    Optional<FeatureDO> findById(String id);

    //@Query("select f.id, f.timestamp, f.beginViewingDate, f.endViewingDate, f.missionName from FeatureDO f")
//    List<FeatureDO> find();
}
