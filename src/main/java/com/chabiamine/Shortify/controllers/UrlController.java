package com.chabiamine.Shortify.controllers;

import com.chabiamine.Shortify.models.Url;
import com.chabiamine.Shortify.repositories.UrlRepository;
import com.chabiamine.Shortify.services.UrlService;
import com.chabiamine.Shortify.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Controller
public class UrlController {

    @Autowired
    UrlService urlService;
    @Autowired
    UrlRepository urlRepository;

    @Autowired
    HashUtil    hashUtil;

    @RequestMapping("/CreateUrl")
    public void CreateUrl (@RequestParam("originalUrl") String originalUrl,
    Model model) {

        try{
            System.out.println(originalUrl);
           String shortlink =  hashUtil.generateShortCode(originalUrl,"aminou");

           Url url = Url.builder().name("test").original_URL(originalUrl).short_URL(shortlink).build();
            System.out.println(url);
            urlRepository.save(url);
           model.addAttribute("url", url);


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/Home")
    public String home(ModelMap modelMap){
        /* add ModelMap as an arguments ain order to use it later on
        * */
        ArrayList<Url> urlList = (ArrayList<Url>) urlRepository.findAll();
        modelMap.addAttribute("urlsListJsp",urlList);
        return "landingpage" ;
    }

    @RequestMapping("/Dashboard")
    public String Dashboard(ModelMap modelMap){


        return "dashboard" ;
    }
    /*
    *
    * */

    @RequestMapping("/deleteUrl")
    public String deleteUrl(@RequestParam("id")Long id,
                            ModelMap modelMap){
        urlService.deleteUrlbyId(id);
        ArrayList<Url> urlList = (ArrayList<Url>) urlRepository.findAll();
        modelMap.addAttribute("urlsListJsp",urlList);
        return "home";
    }




}
