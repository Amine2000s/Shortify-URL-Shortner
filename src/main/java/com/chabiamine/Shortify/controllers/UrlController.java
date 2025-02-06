package com.chabiamine.Shortify.controllers;

import com.chabiamine.Shortify.models.Url;
import com.chabiamine.Shortify.repositories.UrlRepository;
import com.chabiamine.Shortify.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UrlController {

    @Autowired
    UrlService urlService;
    @Autowired
    UrlRepository urlRepository;

    @RequestMapping("/CreateUrl")
    public String createeUrl(){

        return "createUrl" ;
    }

    @RequestMapping("/Home")
    public String home(ModelMap modelMap){

        /* add ModelMap as an arguments ain order to use it later on



        * */

        ArrayList<Url> urlList = (ArrayList<Url>) urlRepository.findAll();
        modelMap.addAttribute("urlsListJsp",urlList);
        return "home" ;
    }
    /*
    *
    * */

}
