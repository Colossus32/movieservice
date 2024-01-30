package com.colossus.movieservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "favorites")
public class FavoriteMovie extends Movie {

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
