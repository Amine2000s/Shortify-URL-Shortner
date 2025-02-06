package com.chabiamine.Shortify;

import com.chabiamine.Shortify.models.Url;
import com.chabiamine.Shortify.repositories.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class ShortifyApplicationTests {


	@Autowired
	UrlRepository urlRepository ;
	@Test
	public void addURL(){

		Url url = new Url("twitter",1L,"www.twitter.com/100063966673456","www.Shortify.com/abg", LocalDate.now());

		urlRepository.save(url);
	}
	@Test
	public void getAllUrls(){

		ArrayList<Url> urls = (ArrayList<Url>) urlRepository.findAll();

		for(Url arr : urls) System.out.println(arr.toString());
	}

}
