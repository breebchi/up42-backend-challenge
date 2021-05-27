package com.up42.chanllenge.controller.mapper;

import com.up42.chanllenge.datatransferobject.FeatureDTO;
import com.up42.chanllenge.domainobject.FeatureDO;

import java.util.List;
import java.util.stream.Collectors;

public class FeatureMapper
{

    private FeatureMapper()
    {
        throw new IllegalStateException("Utility class");
    }


    public static FeatureDTO makeFeatureDTO(FeatureDO featureDO)
    {
        return FeatureDTO.newBuilder()
            .setId(featureDO.getId())
            .setBeginViewingDate(featureDO.getBeginViewingDate())
            .setEndViewingDate(featureDO.getEndViewingDate())
            .setTimestamp(featureDO.getTimestamp())
            .setMissionName(featureDO.getMissionName())
            .createFeatureDTO();
    }


    public static List<FeatureDTO> makeFeatureDTOList(List<FeatureDO> features)
    {
        return features.stream()
            .map(FeatureMapper::makeFeatureDTO)
            .collect(Collectors.toList());
    }
}
