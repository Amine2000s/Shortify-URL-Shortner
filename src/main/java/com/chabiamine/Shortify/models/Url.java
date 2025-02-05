package com.chabiamine.Shortify.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name ;

    private long users_ID ;

    private String original_URL ;


    private String short_URL ;


    private int number_of_visits ;


    public Url(String name, long users_ID, String original_URL, String short_URL) {
        this.name = name;
        this.users_ID = users_ID;
        this.original_URL = original_URL;
        this.short_URL = short_URL;
        this.number_of_visits = 0 ;
    }

    public Url() {
    }

    @Override
    public String toString() {
        return "Links{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users_ID=" + users_ID +
                ", original_URL='" + original_URL + '\'' +
                ", short_URL='" + short_URL + '\'' +
                ", number_of_visits=" + number_of_visits +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUsers_ID() {
        return users_ID;
    }

    public void setUsers_ID(long users_ID) {
        this.users_ID = users_ID;
    }

    public String getOriginal_URL() {
        return original_URL;
    }

    public void setOriginal_URL(String original_URL) {
        this.original_URL = original_URL;
    }

    public String getShort_URL() {
        return short_URL;
    }

    public void setShort_URL(String short_URL) {
        this.short_URL = short_URL;
    }

    public int getNumber_of_visits() {
        return number_of_visits;
    }

    public void setNumber_of_visits(int number_of_visits) {
        this.number_of_visits = number_of_visits;
    }
}
