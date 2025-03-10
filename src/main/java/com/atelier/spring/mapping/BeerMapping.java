package com.atelier.spring.mapping;


import com.atelier.spring.DTO.BeerDTO;
import com.atelier.spring.entity.Beer;
import org.springframework.stereotype.Component;

@Component
public class BeerMapping {
    public Beer dtoToEntity(BeerDTO beerDTO){
        return Beer.builder()
                .uid(beerDTO.uid())
                .name(beerDTO.name())
                .brand(beerDTO.brand())
                .style(beerDTO.style())
                .hop(beerDTO.hop())
                .yeast(beerDTO.yeast())
                .malts(beerDTO.malts())
                .ibu(beerDTO.ibu())
                .alcohol(beerDTO.alcohol())
                .blg(beerDTO.blg())
                .build();
    }

    public BeerDTO entityToDTO(Beer beer){
        return BeerDTO.builder()
                .uid(beer.getUid())
                .name(beer.getName())
                .brand(beer.getBrand())
                .style(beer.getStyle())
                .hop(beer.getHop())
                .yeast(beer.getYeast())
                .malts(beer.getMalts())
                .ibu(beer.getIbu())
                .alcohol(beer.getAlcohol())
                .blg(beer.getBlg())
                .build();
    }
}
