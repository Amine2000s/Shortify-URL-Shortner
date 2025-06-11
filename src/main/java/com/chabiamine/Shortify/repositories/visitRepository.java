package com.chabiamine.Shortify.repositories;

import com.chabiamine.Shortify.models.UrlVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface visitRepository extends JpaRepository<UrlVisit,Long> {
}
