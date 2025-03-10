package com.atelier.spring.service;

import com.atelier.spring.DTO.BeerDTO;
import com.atelier.spring.DTO.ClientDTO;
import com.atelier.spring.entity.Beer;
import com.atelier.spring.mapping.BeerMapping;
import com.atelier.spring.repository.BeerRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapping beerMapping;

    private static final String TARGET_HOST = "https://random-data-api.com/api/v2/beers";
    private final WebClient webClient;

    public BeerService(WebClient.Builder webClientBuilder,
                       BeerRepository beerRepository,
                       BeerMapping beerMapping) {
        this.webClient = webClientBuilder.baseUrl(TARGET_HOST).build();
        this.beerRepository = beerRepository;
        this.beerMapping = beerMapping;
    }

    public BeerDTO getNewBeer(){
        BeerDTO response = webClient
                .get()
                .uri("/")
                .retrieve()
                .bodyToMono(BeerDTO.class)
                .block();

        beerRepository.save(beerMapping.dtoToEntity(response));

        return response;
    }

    public List<BeerDTO> getAllBeers(){
        Iterable<Beer> beers = beerRepository.findAll();
        List<BeerDTO> allBeers = new ArrayList<>();

        beers.forEach(beer -> {
            allBeers.add(beerMapping.entityToDTO(beer));
        });

        return allBeers;
    }

    public BeerDTO getBeerById(Long id){
        return beerMapping.entityToDTO(beerRepository.findById(id).get());
    }

    public List<BeerDTO> getBeersByStyle(String style) {
        Iterable<Beer> beers = beerRepository.findBeerByStyle(style);

        List<BeerDTO> allBeers = new ArrayList<>();

        beers.forEach(beer -> {
            allBeers.add(beerMapping.entityToDTO(beer));
        });

        return allBeers;
    }

    public List<BeerDTO> getBeersByBrandAndName(String brand, String name) {
        Iterable<Beer> beers = beerRepository.findBeerByBrandAndName(brand, name);

        List<BeerDTO> allBeers = new ArrayList<>();

        beers.forEach(beer -> {
            allBeers.add(beerMapping.entityToDTO(beer));
        });

        return allBeers;
    }

    public List<BeerDTO> getBeersByBrand(String brand) {
        Iterable<Beer> beers = beerRepository.findBeerByBrand(brand);

        List<BeerDTO> allBeers = new ArrayList<>();

        beers.forEach(beer -> {
            allBeers.add(beerMapping.entityToDTO(beer));
        });

        return allBeers;
    }

    public List<BeerDTO> getBeersByName(String name) {
        Iterable<Beer> beers = beerRepository.findBeerByName(name);

        List<BeerDTO> allBeers = new ArrayList<>();

        beers.forEach(beer -> {
            allBeers.add(beerMapping.entityToDTO(beer));
        });

        return allBeers;
    }

    public boolean deleteBeer(Long id){
        try{
            beerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public BeerDTO modifyBeer(BeerDTO beerDTO, Long id){
        try{
            Beer beer = beerMapping.dtoToEntity(beerDTO);
            Beer found = beerRepository.findById(id).orElse(null);
            if (found != null){
                found.setUid(beer.getUid());
                found.setName(beer.getName());
                found.setBrand(beer.getBrand());
                found.setStyle(beer.getStyle());
                found.setHop(beer.getHop());
                found.setYeast(beer.getYeast());
                found.setMalts(beer.getMalts());
                found.setIbu(beer.getIbu());
                found.setAlcohol(beer.getAlcohol());
                found.setBlg(beer.getBlg());
                beerRepository.save(found);
            }
            return beerMapping.entityToDTO(beer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BeerDTO createBeer(BeerDTO beerDTO){
        beerRepository.save(beerMapping.dtoToEntity(beerDTO));
        return beerDTO;
    }

    public List<BeerDTO> getNewBeerScapping(int number){
        List<BeerDTO> beers = new ArrayList<>();

        List<BeerDTO> response = webClient
                .get()
                .uri( uriBuilder ->
                    uriBuilder.path("/")
                            .queryParam("size", number)
                            .build()

                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<BeerDTO>>() {})
                .block();

        response.forEach(beer -> {
            beerRepository.save(beerMapping.dtoToEntity(beer));
            beers.add(beer);
        });

        return beers;
    }

    public BeerDTO rebondirBeer(){
        BeerDTO response = webClient
                .get()
                .uri("/")
                .retrieve()
                .bodyToMono(BeerDTO.class)
                .block();


        return response;
    }
}
