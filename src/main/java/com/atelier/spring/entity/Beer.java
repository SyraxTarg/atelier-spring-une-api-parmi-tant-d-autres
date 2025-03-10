package com.atelier.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "beers")
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;

    private String brand;

    private String name;

    private String style;

    private String hop;

    private String yeast;

    private String malts;

    private String ibu;

    private String alcohol;

    private String blg;

}