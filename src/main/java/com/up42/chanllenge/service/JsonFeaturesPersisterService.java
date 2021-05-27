package com.up42.chanllenge.service;

import com.up42.chanllenge.dataaccessobject.FeatureRepository;
import com.up42.chanllenge.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class JsonFeaturesPersisterService
{
    private static final String FILE_PATH = "src/main/resources/source-data.json";
    private final FeatureRepository featureRepository;


    @Autowired
    public JsonFeaturesPersisterService(FeatureRepository featureRepository)
    {
        this.featureRepository = featureRepository;
    }


    @Transactional
    public void saveFeatures() throws IOException
    {
        featureRepository.saveAll(JsonParser.parseFeatures(FILE_PATH));
    }
}
