package com.chabiamine.Shortify.repositories;

import com.chabiamine.Shortify.models.UrlVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface visitRepository extends JpaRepository<UrlVisit,Long> {

    List<UrlVisit> findAllByUrlId(Long urlId);

    long countByUrlId(Long urlId);

    Optional<UrlVisit> findFirstByUrlIdOrderByTimestampAsc(Long urlId);
    Optional<UrlVisit> findFirstByUrlIdOrderByTimestampDesc(Long urlId);

    @Query("SELECT v.referer, COUNT(v) FROM UrlVisit v WHERE v.url.id = :urlId GROUP BY v.referer")
    List<Object[]> findTopReferrers(@Param("urlId") Long urlId);

    @Query("SELECT v.userAgent, COUNT(v) FROM UrlVisit v WHERE v.url.id = :urlId GROUP BY v.userAgent")
    List<Object[]> findDeviceTypes(@Param("urlId") Long urlId);



}
