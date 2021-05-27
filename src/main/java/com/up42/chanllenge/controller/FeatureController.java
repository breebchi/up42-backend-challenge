package com.up42.chanllenge.controller;

import com.up42.chanllenge.controller.mapper.FeatureMapper;
import com.up42.chanllenge.datatransferobject.FeatureDTO;
import com.up42.chanllenge.exception.EntityNotFoundException;
import com.up42.chanllenge.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@Validated
@RequestMapping("v1/features")
public class FeatureController
{
    private final FeatureService featureService;


    @Autowired
    public FeatureController(FeatureService featureService)
    {
        this.featureService = featureService;
    }


    @GetMapping("/{id}")
    public FeatureDTO findFeature(@PathVariable String id) throws EntityNotFoundException
    {
        return FeatureMapper.makeFeatureDTO(featureService.find(id));
    }


    @GetMapping
    public List<FeatureDTO> findAll()
    {
        return FeatureMapper.makeFeatureDTOList(featureService.find());
    }


    @GetMapping(value = "/{id}/quicklook",
        produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getQuickLook(@PathVariable String id) throws EntityNotFoundException, SQLException
    {
        return featureService.getQuickLook(id);
    }
}
