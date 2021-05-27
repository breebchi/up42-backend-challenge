package com.up42.chanllenge.service.feature;

import com.up42.chanllenge.dataaccessobject.FeatureRepository;
import com.up42.chanllenge.domainobject.FeatureDO;
import com.up42.chanllenge.exception.EntityNotFoundException;
import com.up42.chanllenge.service.FeatureService;
import com.up42.chanllenge.util.BlobConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * This is a class that services the endpoint's requests
 */
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
        // Here we convert the Blob to byte array and return it.
        // The initial idea was to returned a BufferedImage, but this is much faster as it seems in this use case.
        return BlobConverter.convertBlobToByteArray(feature.getQuicklook());
    }
}
