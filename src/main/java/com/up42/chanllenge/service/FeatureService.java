package com.up42.chanllenge.service;

import com.up42.chanllenge.domainobject.FeatureDO;
import com.up42.chanllenge.exception.EntityNotFoundException;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface represents a contract as to what logic a feature service class should implement.
 */
public interface FeatureService
{
    FeatureDO find(String featureId) throws EntityNotFoundException;

    List<FeatureDO> find();

    byte[] getQuickLook(String featureId) throws EntityNotFoundException, SQLException;
}
