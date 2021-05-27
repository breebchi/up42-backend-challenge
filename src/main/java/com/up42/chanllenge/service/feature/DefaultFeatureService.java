package com.up42.chanllenge.service.feature;

import com.up42.chanllenge.dataaccessobject.FeatureRepository;
import com.up42.chanllenge.domainobject.FeatureDO;
import com.up42.chanllenge.exception.EntityNotFoundException;
import com.up42.chanllenge.service.FeatureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Service
public class DefaultFeatureService implements FeatureService
{
    private final FeatureRepository featureRepository;


    public DefaultFeatureService(FeatureRepository featureRepository)
    {
        this.featureRepository = featureRepository;
    }


    @Transactional
    @Override
    public FeatureDO find(String featureId) throws EntityNotFoundException
    {
        return featureRepository.findById(featureId).orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + featureId));
    }


    @Transactional
    @Override
    public List<FeatureDO> find()
    {
        return featureRepository.findAll();
    }


    @Transactional
    @Override
    public byte[] getQuickLook(String featureId) throws EntityNotFoundException, SQLException
    {
        FeatureDO feature =
            featureRepository.findById(featureId).orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + featureId));
        Blob blob = feature.getQuicklook();
        byte[] bytes = blob.getBytes(1, (int) blob.length());
        blob.free();
        return bytes;
    }
}
