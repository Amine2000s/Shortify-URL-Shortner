package com.chabiamine.Shortify.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UrlVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visitId;

    private String ipAdress ;

    private String userAgent;

    private String referer;

    private String country;

    private LocalDateTime timestamp ;

    @ManyToOne
    @JoinColumn(name = "id")
    private Url url ;




}
