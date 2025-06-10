package com.chabiamine.Shortify.controllers;


import com.chabiamine.Shortify.models.Url;
import com.chabiamine.Shortify.repositories.UrlRepository;
import com.chabiamine.Shortify.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@RequestMapping("/Shortify")
public class ShorteningController {

    @Autowired
    HashUtil hashUtil;

    @Autowired
    UrlRepository urlRepository;

    public ShorteningController(HashUtil hashUtil, UrlRepository urlRepository) {
        this.hashUtil = hashUtil;
        this.urlRepository = urlRepository;
    }

    @GetMapping("/{shortcode}")
    public ResponseEntity<String> redirectToOriginalUrl(@PathVariable String shortcode) {

        try {
            Optional<Url> url = Optional.ofNullable(urlRepository.findByShortCode(shortcode));
            System.out.println(url);
            String originalUrl = url.get().getOriginal_URL();
            return  ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(originalUrl))
                    .build();

        }catch (Exception e){

            e.printStackTrace();

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

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
