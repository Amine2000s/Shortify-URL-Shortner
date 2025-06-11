package com.chabiamine.Shortify.controllers;


import com.chabiamine.Shortify.dto.LinkAnalyticsDto;
import com.chabiamine.Shortify.models.Url;
import com.chabiamine.Shortify.models.UrlVisit;
import com.chabiamine.Shortify.repositories.UrlRepository;
import com.chabiamine.Shortify.repositories.visitRepository;
import com.chabiamine.Shortify.services.UrlService;
import com.chabiamine.Shortify.utils.HashUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Shortify")
public class ShorteningController {

    @Autowired
    HashUtil hashUtil;

    @Autowired
    UrlRepository urlRepository;

    @Autowired
    visitRepository visitRepository;

    @Autowired
    UrlService urlService;

    public ShorteningController(HashUtil hashUtil,
                                UrlRepository urlRepository,
                                visitRepository visitRepository,
                                UrlService urlService) {
        this.hashUtil = hashUtil;
        this.urlRepository = urlRepository;
        this.visitRepository = visitRepository;
        this.urlService = urlService;
    }

    @GetMapping("/{shortcode}")
    public ResponseEntity<String> redirectToOriginalUrl(@PathVariable String shortcode,
                                                        HttpServletRequest request) throws IOException {




            Optional<Url> url = Optional.ofNullable(urlRepository.findByShortCode(shortcode));
            if(url.isPresent()) {

                UrlVisit visit = new UrlVisit();
                visit.setIpAdress(urlService.getClientIp(request)); // for later on when we deploy
                visit.setUserAgent(request.getHeader("User-Agent"));
                visit.setReferer(request.getHeader("Referer"));
                visit.setTimestamp(LocalDateTime.now());
                visit.setUrl(url.get());
                visit.setCountry(urlService.GetCountry(request.getRemoteAddr()));
                visitRepository.save(visit);

                url.get().setNumber_of_visits(url.get().getNumber_of_visits() + 1);
                urlRepository.save(url.get());

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
        Optional<Url> url  = urlRepository.findById(id);
        String newName = body.get("name");
        url.get().setName(newName);
        urlRepository.save(url.get());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/analytics")
    @ResponseBody
    public ResponseEntity<LinkAnalyticsDto> getAnalytics(@PathVariable Long id) {
        // Dummy data for now (replace with real service later)
        LinkAnalyticsDto dto = new LinkAnalyticsDto();
        dto.totalClicks = 432;
        dto.firstClick = "2024-05-01";
        dto.lastClick = "2025-06-08";
        dto.topCountries = Map.of("United States", 168, "France", 122, "Germany", 78);
        dto.deviceTypes = Map.of("Mobile", 68, "Desktop", 28, "Bot", 4);
        dto.referrers = Map.of("google.com", 52, "twitter.com", 33, "(direct)", 15);

        return ResponseEntity.ok(dto);
    }

}
