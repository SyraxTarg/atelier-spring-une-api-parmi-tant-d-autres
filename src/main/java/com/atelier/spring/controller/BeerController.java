package com.atelier.spring.controller;

import com.atelier.spring.DTO.BeerDTO;
import com.atelier.spring.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BeerController {

    private final BeerService beerService;

    // CRUD -> role_user

    @PostMapping("/user/beers/new")
    public BeerDTO createBeer(@RequestBody BeerDTO beerDTO) {
        return beerService.createBeer(beerDTO);
    }

    @GetMapping("/user/beers")
    public List<BeerDTO> getAllBeers(){
        return beerService.getAllBeers();
    }

    @GetMapping("/user/beers/{id}")
    public BeerDTO getBeerById(@PathVariable("id") Long id) {
        return beerService.getBeerById(id);
    }

    @GetMapping("/user/beers/filter")
    public List<BeerDTO> getBeersByFilter(@RequestParam(required = false) String style,
                                          @RequestParam(required = false) String brand,
                                          @RequestParam(required = false) String name) {

        List<BeerDTO> beers = new ArrayList<>();
        if(style != null){
            beers = beerService.getBeersByStyle(style);
        }
        else if (brand != null && name != null){
            beers = beerService.getBeersByBrandAndName(brand, name);
        }
        else if (brand != null){
            beers = beerService.getBeersByBrand(brand);
        }
        else if (name != null){
            beers = beerService.getBeersByName(name);
        }
        return beers;
    }

    @DeleteMapping("/user/beers/{id}")
    public String deleteBeer(@PathVariable("id") Long id) {
        return beerService.deleteBeer(id) ? "Beer deleted" : "Error";
    }

    @PatchMapping("/user/beers/{id}")
    public BeerDTO patchBeer(@RequestBody BeerDTO beerDTO, @PathVariable("id") Long id) {
        return beerService.modifyBeer(beerDTO, id);
    }

    // SCRAPPING -> role_scrap

    @GetMapping("/scrapper/beers/new")
    public BeerDTO newBeer() {
        return beerService.getNewBeer();
    }

    @GetMapping("/scrapper/beers")
    public List<BeerDTO> getNewBeerScrapping(@RequestParam int number){
        return beerService.getNewBeerScapping(number);
    }

    // REBOND DE REQUETE -> role_rebond

    @GetMapping("/rebond/beers")
    public BeerDTO rebondirBeers() {
        return beerService.rebondirBeer();
    }
}
