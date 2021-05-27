package com.up42.chanllenge.dataaccessobject;

import com.up42.chanllenge.domainobject.FeatureDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface serves as a repository for the feature domain.
 */
@Repository
public interface FeatureRepository extends JpaRepository<FeatureDO, String>
{
    // We override this method to have a string as an Id
    @Override
    Optional<FeatureDO> findById(String id);
}
