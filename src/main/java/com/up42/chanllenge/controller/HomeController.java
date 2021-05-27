package com.up42.chanllenge.controller;

import com.up42.chanllenge.util.JsonFeaturesPersister;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;

/**
 * This controller is used by Swagger UI
 */
@Controller
@ApiIgnore
public class HomeController
{
    private final JsonFeaturesPersister jsonFeaturesPersister;


    public HomeController(JsonFeaturesPersister jsonFeaturesPersister)
    {
        // Here we make it so that the data is initialized.
        // It is not possible to use @PostConstruct since no Hibernate session would start yet at the time.
        this.jsonFeaturesPersister = jsonFeaturesPersister;
    }


    @RequestMapping("/")
    public String home() throws IOException
    {
        // This serves the Swagger UI page upon call to "/"
        jsonFeaturesPersister.saveFeatures();
        return "redirect:swagger-ui.html";
    }

}
