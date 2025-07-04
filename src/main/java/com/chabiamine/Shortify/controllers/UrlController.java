package com.chabiamine.Shortify.controllers;

import com.chabiamine.Shortify.models.Url;
import com.chabiamine.Shortify.models.Users;
import com.chabiamine.Shortify.repositories.UrlRepository;
import com.chabiamine.Shortify.repositories.UsersRepository;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Controller
public class UrlController {

    @Autowired
    UrlService urlService;

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    HashUtil    hashUtil;

    @Autowired
    UsersRepository usersRepository ;

    //TODO : this is a newbie way , modify it !
    //private static final String BASE_URL = "http://localhost:8085/Shortify";





    @RequestMapping("/CreateUrl")
    public String CreateUrl (@RequestParam("originalUrl") String originalUrl,
                              @RequestParam("newUrlName")String newUrlName,
                             HttpServletRequest request,
                              Principal principal,
                              Model model) {

        try{

            //TODO : username must be the currently signed username
           String shortlink =  hashUtil.generateShortCode(originalUrl,newUrlName);
            String username = principal.getName();
            Users user = usersRepository.findByUsername(username).orElseThrow();
            String BASE_URL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            System.out.println("###################################################################");
            System.out.println(BASE_URL);
            System.out.println("###################################################################");

            Url url = Url.builder().
                   name(newUrlName).
                   original_URL(originalUrl).
                   shortUrl(BASE_URL+"/Shortify/"+shortlink).
                   shortCode(shortlink).
                   user(user).
                   date_Created(Date.valueOf(LocalDate.now())).
                   build();

            urlRepository.save(url);

            model.addAttribute("url", url);
            System.out.println("###################################################################");

            System.out.println(url.getShort_URL());
            System.out.println(url.getShortUrl());
            System.out.println("###################################################################");


            return "redirect:" + request.getHeader("Referer");

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    //landing page rout
    @RequestMapping("/Home")
    public String home(Model model,
                       Principal principal){
        if (principal != null) return "redirect:/Dashboard";

        model.addAttribute("user", new Users());
        ArrayList<Url> urlList = (ArrayList<Url>) urlRepository.findAll();
        //modelMap.addAttribute("urlsListJsp",urlList);

        return "landingpage" ;
    }

    //dashboard route
    @RequestMapping("/Dashboard")
    public String Dashboard(Principal principal , Model model){

        // TODO : we need to fetch all necessary information before snedoing our dashboard
        /*
        * total links
        * total clicks
        * avg ctr
        * country
        * top performing links
        * */
        //List<Url> userUrls = urlRepository.findAll();
        String username = principal.getName(); // from Spring Security
        List<Url> urls = urlRepository.findAllByUserUsername(username);
        model.addAttribute("username", username);
        model.addAttribute("urls", urls);

        return "dashboard" ;
    }

    @RequestMapping("/deleteUrl")
    public String deleteUrl(@RequestParam("id")Long id,
                            ModelMap modelMap,
                            HttpServletRequest request){
        urlService.deleteUrlbyId(id);
        ArrayList<Url> urlList = (ArrayList<Url>) urlRepository.findAll();
        modelMap.addAttribute("urlsListJsp",urlList);

        return "redirect:" + request.getHeader("Referer");
    }

    //TODO: [X] modify action
    //      [X] analytics action
    //      [X] delete action


}
