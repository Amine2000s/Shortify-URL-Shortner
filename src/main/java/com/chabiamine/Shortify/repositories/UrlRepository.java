package com.chabiamine.Shortify.repositories;

import com.chabiamine.Shortify.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {

    Url findByShortCode(String shortCode);

}
