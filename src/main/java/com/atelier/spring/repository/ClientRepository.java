package com.atelier.spring.repository;

import com.atelier.spring.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAllByGender(String gender);

    @Query(value = """
    SELECT c.* FROM clients c
    JOIN addresses a ON c.address_id = a.id
    WHERE a.country = ?1
    """, nativeQuery = true)
    List<Client> findAllByCountry(String country);

    @Query(value = """
    SELECT c.* FROM clients c
    JOIN addresses a ON c.address_id = a.id
    WHERE a.city = ?1
    """, nativeQuery = true)
    List<Client> findAllByCity(String city);

    @Query(value = "SELECT * FROM clients WHERE date_of_birth < ?1", nativeQuery = true)
    List<Client> findByDate_of_birthBefore(Date date_of_birth);

}
