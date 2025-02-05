package com.chabiamine.Shortify;

import com.chabiamine.Shortify.models.Url;
import com.chabiamine.Shortify.repositories.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShortifyApplicationTests {


	@Autowired
	UrlRepository urlRepository ;
	@Test
	public void addURL(){

		Url url = new Url("facbook",1L,"www.facebook.com/jslkjdslsdfdsf","www.Shortify.com/abhs5u");

		urlRepository.save(url);
	}

}
