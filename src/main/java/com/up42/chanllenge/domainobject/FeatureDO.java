package com.up42.chanllenge.domainobject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

/**
 * This represents a feature.
 */
@Entity
@Table(
    name = "feature",
    uniqueConstraints = @UniqueConstraint(name = "uc_id", columnNames = {"id"})
)
public class FeatureDO
{
    @NotNull(message = "id can not be null!")
    @Id
    @Column(nullable = false)
    private String id;
    @NotNull(message = "timestamp can not be null!")
    @Column(nullable = false)
    private Long timestamp;
    @NotNull(message = "beginViewingDate can not be null!")
    @Column(nullable = false)
    private Long beginViewingDate;
    @NotNull(message = "endViewingDate can not be null!")
    @Column(nullable = false)
    private Long endViewingDate;
    @NotNull(message = "missionName can not be null!")
    @Column(nullable = false)
    private String missionName;
    @NotNull(message = "quicklook can not be null!")
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

}
