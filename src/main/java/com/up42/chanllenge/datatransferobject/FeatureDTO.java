package com.up42.chanllenge.datatransferobject;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.sql.Blob;

public class FeatureDTO
{
    @NotNull(message = "id can not be null!")
    private String id;
    @NotNull(message = "timestamp can not be null!")
    private Long timestamp;
    @NotNull(message = "beginViewingDate can not be null!")
    private Long beginViewingDate;
    @NotNull(message = "endViewingDate can not be null!")
    private Long endViewingDate;
    @NotNull(message = "missionName plate type can not be null!")
    private String missionName;


    public FeatureDTO()
    {
    }


    private FeatureDTO(String id, Long timestamp, Long beginViewingDate, Long endViewingDate, String missionName)
    {
        this.id = id;
        this.timestamp = timestamp;
        this.beginViewingDate = beginViewingDate;
        this.endViewingDate = endViewingDate;
        this.missionName = missionName;
    }


    public static FeatureDTO.FeatureDTOBuilder newBuilder()
    {
        return new FeatureDTO.FeatureDTOBuilder();
    }


    @JsonProperty
    public String getId()
    {
        return id;
    }


    public Long getTimestamp()
    {
        return timestamp;
    }


    public Long getBeginViewingDate()
    {
        return beginViewingDate;
    }


    public Long getEndViewingDate()
    {
        return endViewingDate;
    }


    public String getMissionName()
    {
        return missionName;
    }


    public static final class FeatureDTOBuilder
    {
        private String id;
        private Long timestamp;
        private Long beginViewingDate;
        private Long endViewingDate;
        private String missionName;


        private FeatureDTOBuilder()
        {
        }


        public FeatureDTOBuilder setId(String id)
        {
            this.id = id;
            return this;
        }


        public FeatureDTOBuilder setTimestamp(Long timestamp)
        {
            this.timestamp = timestamp;
            return this;
        }


        public FeatureDTOBuilder setBeginViewingDate(Long beginViewingDate)
        {
            this.beginViewingDate = beginViewingDate;
            return this;
        }


        public FeatureDTOBuilder setEndViewingDate(Long endViewingDate)
        {
            this.endViewingDate = endViewingDate;
            return this;
        }


        public FeatureDTOBuilder setMissionName(String missionName)
        {
            this.missionName = missionName;
            return this;
        }

        public FeatureDTO createFeatureDTO()
        {
            return new FeatureDTO(id, timestamp, beginViewingDate, endViewingDate, missionName);
        }
    }
}
