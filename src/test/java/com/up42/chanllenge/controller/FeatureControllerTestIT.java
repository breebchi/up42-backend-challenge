package com.up42.chanllenge.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.chanllenge.datatransferobject.FeatureDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class tests all endpoints in their common use cases.
 * In real life, it should also implement testing the endpoints for fringe cases.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FeatureControllerTestIT
{

    private static final String FEATURE_CONTROLLER_PATH = "/v1/features";
    private static final String HOME_CONTROLLER_PATH = "/";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;


    @Before
    public void setUp() throws Exception
    {
        // Here we initialize the DB with source-data.json
        mockMvc.perform(get(HOME_CONTROLLER_PATH));
    }


    @Test
    public void findFeature() throws Exception
    {
        String endpointPath = "/{id}";
        // Here we initialize a FeatureDTO object to correspond to a feature that's already saved in DB
        FeatureDTO feature = FeatureDTO.newBuilder().setId("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
            .setTimestamp(1554831167697L)
            .setBeginViewingDate(1554831167697L)
            .setEndViewingDate(1554831202043L)
            .setMissionName("Sentinel-1B")
            .createFeatureDTO();

        // This calls the rest endpoint and expects results to compare them with the expected values.
        mockMvc.perform(get(FEATURE_CONTROLLER_PATH + endpointPath, feature.getId())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(feature.getId()))
            .andExpect(jsonPath("$.timestamp").value(feature.getTimestamp()))
            .andExpect(jsonPath("$.beginViewingDate").value(feature.getBeginViewingDate()))
            .andExpect(jsonPath("$.endViewingDate").value(feature.getEndViewingDate()))
            .andExpect(jsonPath("$.missionName").value(feature.getMissionName()));
    }


    @Test
    public void findAll() throws Exception
    {
        // Here we initialize a FeatureDTO object to correspond to a feature that's already saved in DB
        FeatureDTO feature = FeatureDTO.newBuilder().setId("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
            .setTimestamp(1554831167697L)
            .setBeginViewingDate(1554831167697L)
            .setEndViewingDate(1554831202043L)
            .setMissionName("Sentinel-1B")
            .createFeatureDTO();

        // This calls the rest endpoint and expects results to compare them with what's expected.
        MvcResult result = mockMvc.perform(get(FEATURE_CONTROLLER_PATH)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andReturn();

        // This converts a JSON string to a list. We inferred the type FeatureDTO for our resource to be mapped to.
        List<FeatureDTO> listOfFeatures = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<FeatureDTO>>()
        {
        });

        // Here we pick one of the expected features and compare its content to the expected values.
        assertEquals(14, listOfFeatures.size());
        assertEquals(feature.getId(), listOfFeatures.get(0).getId());
        assertEquals(feature.getTimestamp(), listOfFeatures.get(0).getTimestamp());
        assertEquals(feature.getBeginViewingDate(), listOfFeatures.get(0).getBeginViewingDate());
        assertEquals(feature.getEndViewingDate(), listOfFeatures.get(0).getEndViewingDate());
        assertEquals(feature.getMissionName(), listOfFeatures.get(0).getMissionName());
    }


    @Test
    public void getQuickLook() throws Exception
    {
        String endpointPath = "/{id}/quicklook";
        // This is the id of a feature that's stored on db.
        String id = "39c2f29e-c0f8-4a39-a98b-deed547d6aea";
        // This is the path of a "quicklook" resource associated with the feature which we introduced.
        // We extracted it a file to use it as an expected resource and compare to fetched resource.
        String pathOfQuickLook =  "src/main/resources/quicklook-featureId-39c2f29e-c0f8-4a39-a98b-deed547d6aea.txt";
        String quickLook = readLineByLine(pathOfQuickLook).trim();

        // This calls the rest endpoint and expects results to compare them with what's expected.
        MvcResult result = mockMvc.perform(get(FEATURE_CONTROLLER_PATH + endpointPath, id)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andReturn();
        assertEquals(quickLook, result.getResponse().getContentAsString());
    }

    // This reads a a file into a string. We use it to read the data-source.json file in this class.
    private static String readLineByLine(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }
}
