package com.up42.chanllenge.controller;

import com.up42.chanllenge.service.JsonFeaturesPersisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;

@Controller
@ApiIgnore
public class HomeController
{
    private final JsonFeaturesPersisterService jsonFeaturesPersisterService;


    public HomeController(JsonFeaturesPersisterService jsonFeaturesPersisterService)
    {
        this.jsonFeaturesPersisterService = jsonFeaturesPersisterService;
    }


    @RequestMapping("/")
    public String home() throws IOException
    {
        jsonFeaturesPersisterService.saveFeatures();
        return "redirect:swagger-ui.html";
    }

}
