package com.chabiamine.Shortify.controllers;


import com.chabiamine.Shortify.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/Shortify")
public class ShorteningController {

@Autowired
    HashUtil hashUtil;

    //redirect the corresponding shortned url to the original one
    @GetMapping("/{shortcode}")
public String redirectToOriginalUrl(@PathVariable String shortcode) {

        try {
            //String originalUrl =

        }catch (Exception e){

            e.printStackTrace();
            return "error not found ";
        }
return "numm";

    }


    // return the new shortned url
    @PostMapping("")
public void shorten(@RequestParam String url) {

    }


    @GetMapping("/Hello")
    public String hello() throws NoSuchAlgorithmException {
        String url =hashUtil.generateShortCode("www.facebook.com","aminou");
        System.out.println("Shortify url: " + url);
        return  "<H1>"+url +"</H1>";

    }

}
