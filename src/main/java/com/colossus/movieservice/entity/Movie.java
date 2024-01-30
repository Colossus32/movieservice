package com.colossus.movieservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "movies")
public class Movie {

    public Movie() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String nameRu;

    @Column(nullable = false, unique = true)
    private String posterURL;

}
