package com.up42.chanllenge.domainobject;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

@Entity
@Table(
    name = "feature",
    uniqueConstraints = @UniqueConstraint(name = "uc_id", columnNames = {"id"})
)
public class FeatureDO
{
    @Id
    @Column(nullable = false)
    @NotNull(message = "License plate type can not be null!")
    private String id;
    @Column(nullable = false)
    private Long timestamp;
    @Column(nullable = false)
    private Long beginViewingDate;
    @Column(nullable = false)
    private Long endViewingDate;
    @Column(nullable = false)
    private String missionName;
    @Column(nullable = false)
    @Lob
    private Blob quicklook;


    public FeatureDO()
    {
    }


    public FeatureDO(String id, Long timestamp, Long beginViewingDate, Long endViewingDate, String missionName, Blob quicklook)
    {
        this.id = id;
        this.timestamp = timestamp;
        this.beginViewingDate = beginViewingDate;
        this.endViewingDate = endViewingDate;
        this.missionName = missionName;
        this.quicklook = quicklook;
    }


    public Blob getQuicklook()
    {
        return quicklook;
    }


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


    public void setMissionName(String missionName)
    {
        this.missionName = missionName;
    }
}
