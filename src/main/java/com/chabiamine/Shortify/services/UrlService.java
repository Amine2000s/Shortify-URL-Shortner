package com.chabiamine.Shortify.services;

import com.chabiamine.Shortify.models.Url;
import com.chabiamine.Shortify.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository ;

    public List<Url> getUrls(Long id){

        return urlRepository.findAll();

    }
    public ArrayList<Url> getAllUrls(){

        return (ArrayList<Url>) urlRepository.findAll();

    }

    public void addUrl(Url url ){
        // need to perform hash generation

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

}
