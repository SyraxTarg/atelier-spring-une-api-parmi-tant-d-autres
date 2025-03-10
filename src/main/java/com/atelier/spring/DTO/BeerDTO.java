package com.atelier.spring.DTO;


import lombok.Builder;

@Builder
public record BeerDTO(
                             String uid,
                             String name,
                             String brand,
                             String style,
                             String hop,
                             String yeast,
                             String malts,
                             String ibu,
                             String alcohol,
                             String blg) {
}
