package com.chabiamine.Shortify.services;

import com.chabiamine.Shortify.models.Url;
import com.chabiamine.Shortify.repositories.UrlRepository;
import com.chabiamine.Shortify.utils.HashUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository ;

    @Autowired
    HashUtil hashUtil;

    public List<Url> getUrls(Long id){

        return urlRepository.findAll();

    }

    public ArrayList<Url> getAllUrls(){

        return (ArrayList<Url>) urlRepository.findAll();

    }

    public void ShortenUrl(Url url ){




        urlRepository.save(url);

    }

    public void modifyUrl(Url newUrl){

        urlRepository.save(newUrl);


    }

    public void deleteUrl(Url url){

        urlRepository.delete(url);
    }
    public void deleteUrlbyId(Long id){

        Url url = urlRepository.getReferenceById(id);

        urlRepository.delete(url);
    }


    public String GetCountry(String ipAdress) throws IOException {
        URL url = new URL("https://ipwho.is/" + ipAdress);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response = in.lines().collect(Collectors.joining());
        JSONObject json = new JSONObject(response);
        //System.out.println(json.getString("country"));
        return json.getString("country");
    }

    public String getClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return request.getRemoteAddr();
    }



}
