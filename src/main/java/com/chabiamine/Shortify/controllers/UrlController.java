package com.chabiamine.Shortify.controllers;

import com.chabiamine.Shortify.models.Url;
import com.chabiamine.Shortify.repositories.UrlRepository;
import com.chabiamine.Shortify.services.UrlService;
import com.chabiamine.Shortify.utils.HashUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.eclipse.tags.shaded.org.apache.xpath.objects.XString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Controller
public class UrlController {

    @Autowired    private static final String BASE_URL = "http://localhost:8085/Shortify";
    UrlService urlService;
    @Autowired
    UrlRepository urlRepository;

    @Autowired
    HashUtil    hashUtil;

    @RequestMapping("/CreateUrl")
    public String CreateUrl (@RequestParam("originalUrl") String originalUrl,
                              @RequestParam("newUrlName")String newUrlName,
                             HttpServletRequest request,
                              Model model) {

        try{

            System.out.println(originalUrl);
           String shortlink =  hashUtil.generateShortCode(originalUrl,"aminou");


           Url url = Url.builder().
                   name(newUrlName).
                   original_URL(originalUrl).
                   shortUrl(BASE_URL+"/"+shortlink).
                   shortCode(shortlink).
                   date_Created(Date.valueOf(LocalDate.now())).
                   build();

            System.out.println(url);
            urlRepository.save(url);

            model.addAttribute("url", url);

            return "redirect:" + request.getHeader("Referer");

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
    public String Dashboard(Model model){

        List<Url> userUrls = urlRepository.findAll();

        model.addAttribute("urls", userUrls);

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
