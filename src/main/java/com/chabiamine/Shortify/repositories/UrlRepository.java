package com.chabiamine.Shortify.repositories;

import com.chabiamine.Shortify.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {

    Url findByShortCode(String shortCode);
    List<Url> findAllByUserUsername(String user_username);
}
