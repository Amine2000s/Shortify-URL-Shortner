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
import java.util.Map;
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


            Optional<Url> url = Optional.ofNullable(urlRepository.findByShortCode(shortcode));
            if(url.isPresent()) {
                String originalUrl = url.get().getOriginal_URL();
                return  ResponseEntity.status(HttpStatus.FOUND)
                        .location(URI.create(originalUrl))
                        .build();
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUrl(@PathVariable Long id) {
        urlRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUrl(@PathVariable Long id,@RequestBody Map<String, String> body) {
        System.out.println("hello");
        Optional<Url> url  = urlRepository.findById(id);
        String newName = body.get("name");
        url.get().setName(newName);
        urlRepository.save(url.get());
        return ResponseEntity.ok().build();
    }



}
