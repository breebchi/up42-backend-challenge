package com.up42.chanllenge.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.chanllenge.domainobject.FeatureDO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser
{
    public static List<FeatureDO>  parseFeatures(String filePath) throws IOException
    {

        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //read customer.json file into tree model
        //        JsonNode rootNode = objectMapper.readTree(new File(filePath));
        List<FeatureDO> list = new ArrayList<>();

        final JsonNode arrNode = objectMapper.readTree(new File(filePath));
        if (arrNode.isArray())
        {
            for (final JsonNode objNode : arrNode)
            {

                list.add(new FeatureDO(
                    objNode.path("features").get(0).path("properties").path("id").asText(),
                    objNode.path("features").get(0).path("properties").path("timestamp").asLong(),
                    objNode.path("features").get(0).path("properties").path("acquisition")
                        .path("beginViewingDate").asLong(),
                    objNode.path("features").get(0).path("properties").path("acquisition")
                        .path("endViewingDate").asLong(),
                    objNode.path("features").get(0).path("properties").path("acquisition")
                        .path("missionName").asText(),
                    BlobStringInterConverter.convertStringToBlob(objNode.path("features").get(0).path("properties").path("quicklook").asText())
                ));

            }
        }
        return list;
    }
}
