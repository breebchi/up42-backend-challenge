package com.up42.chanllenge.util;

import com.up42.chanllenge.dataaccessobject.FeatureRepository;
import com.up42.chanllenge.domainobject.FeatureDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * This component is used to persist data that is read from a data source json.
 */
@Component
public class JsonFeaturesPersister
{
    private static final String FILE_PATH = "src/main/resources/source-data.json";
    private final FeatureRepository featureRepository;


    @Autowired
    public JsonFeaturesPersister(FeatureRepository featureRepository)
    {
        this.featureRepository = featureRepository;
    }


    @Transactional
    public List<FeatureDO> saveFeatures() throws IOException
    {
        return featureRepository.saveAll(JsonParser.parseFeatures(FILE_PATH));
    }
}
