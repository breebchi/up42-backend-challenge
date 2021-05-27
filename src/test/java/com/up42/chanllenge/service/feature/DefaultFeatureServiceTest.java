package com.up42.chanllenge.service.feature;

import com.up42.chanllenge.dataaccessobject.FeatureRepository;
import com.up42.chanllenge.domainobject.FeatureDO;
import com.up42.chanllenge.exception.EntityNotFoundException;
import com.up42.chanllenge.util.BlobConverter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * This class implements unit tests for the service class.
 * Other unit tests should be implemented for the util package classes.
 */
public class DefaultFeatureServiceTest
{
    // Here we tell Mockito to mock DefaultFeatureService and to inject FeatureRepository into it.
    @InjectMocks
    DefaultFeatureService featureService;
    @Mock
    FeatureRepository featureRepository;


    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById() throws EntityNotFoundException
    {
        // Here we initialize a FeatureDO object
        FeatureDO feature = new FeatureDO("39c2f29e-c0f8-4a39-a98b-deed547d6aea", 1554831167697L,
            1554831167697L, 1554831202043L, "Sentinel-1B", BlobConverter.convertStringToBlob("Test"));
        // Here we tell Mockito what to we want the repository method that we call to return.
        when(featureRepository.findById(feature.getId())).thenReturn(Optional.of(feature));
        // Here we compare the results we got to the expected values.
        assertEquals(feature, featureService.find(feature.getId()));
    }


    @Test(expected = EntityNotFoundException.class)
    public void findByIdShouldThrowEntityNotFoundExceptionWhenFeatureIsNotFound() throws EntityNotFoundException
    {
        // Since we do not tell Mockito what we want the repository method to return, it returns nothing, hence the EntityNotFoundException exception.
        featureService.find("39c2f29e-c0f8-4a39-a98b-deed547d6aea");
    }


    @Test
    public void findAll()
    {
        // Here we initialize FeatureDO objects
        List<FeatureDO> features = new ArrayList<>();
        features.add(new FeatureDO("39c2f29e-c0f8-4a39-a98b-deed547d6aea", 1554831167697L,
            1554831167697L, 1554831202043L, "Sentinel-1B", BlobConverter.convertStringToBlob("Test")));
        features.add(new FeatureDO("39c2f29e-c0f8-4a39-a98b-deed547d6edc", 1554834167697L,
            1554831167697L, 1554831202643L, "Sentinel-2B", BlobConverter.convertStringToBlob("Test2")));
        features.add(new FeatureDO("39c2f29e-c0f8-8525-a98b-deed547d6edc", 1557834167697L,
            1554831167697L, 1554831202643L, "Sentinel-3B", BlobConverter.convertStringToBlob("Test2")));
        // Here we tell Mockito what to we want the repository method that we call to return.
        when(featureRepository.findAll()).thenReturn(features);
        // Here we compare the results we got to the expected values.
        assertEquals(3, featureService.find().size());
        assertEquals(features, featureService.find());
    }


    @Test
    public void findShouldReturnEmptyListWhenNoFeatureWasSpecified()
    {
        // Here we tell Mockito what to we want the repository method that we call to return
        when(featureRepository.findAll()).thenReturn(Collections.emptyList());
        // Here we compare the results we got to the expected values.
        assertEquals(0, featureService.find().size());
    }


    @Test
    public void getQuickLook() throws SQLException, EntityNotFoundException
    {
        FeatureDO feature = new FeatureDO("39c2f29e-c0f8-4a39-a98b-deed547d6aea", 1554831167697L,
            1554831167697L, 1554831202043L, "Sentinel-1B", BlobConverter.convertStringToBlob("Test"));
        // Here we tell Mockito what to we want the repository method that we call to return
        when(featureRepository.findById(feature.getId())).thenReturn(Optional.of(feature));
        // Here we compare the results we got to the expected values.
        assertEquals(BlobConverter.convertBlobToString(feature.getQuicklook()), new String(featureService.getQuickLook(feature.getId())));
    }


    @Test(expected = EntityNotFoundException.class)
    public void getQuickLookShouldThrowEntityNotFoundExceptionWhenFeatureIsNotFound() throws SQLException, EntityNotFoundException
    {
        // Since we do not tell Mockito what we want the repository method to return, it returns nothing, hence the EntityNotFoundException exception.
        featureService.getQuickLook("39c2f29e-c0f8-4a39-a98b-deed547d6aea");
    }
}
