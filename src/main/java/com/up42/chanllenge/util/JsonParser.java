package com.up42.chanllenge.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.chanllenge.domainobject.FeatureDO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class defines how we parse the specific data we want from the data source json.
 */
public class JsonParser
{
    public static List<FeatureDO> parseFeatures(String filePath) throws IOException
    {

        ObjectMapper objectMapper = new ObjectMapper();

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
                    BlobConverter.convertStringToBlob(objNode.path("features").get(0).path("properties").path("quicklook").asText())
                ));

            }
        }
        return list;
    }
}
