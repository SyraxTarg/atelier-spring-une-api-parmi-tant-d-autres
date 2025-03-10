package com.atelier.spring.repository;

import com.atelier.spring.entity.Beer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerRepository extends CrudRepository<Beer, Long> {

    List<Beer> findBeerByStyle(String style);

    List<Beer> findBeerByBrandAndName(String brand, String name);

    List<Beer> findBeerByBrand(String brand);

    List<Beer> findBeerByName(String name);
}
