package com.atelier.spring.controller;

import com.atelier.spring.DTO.ClientDTO;
import com.atelier.spring.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ClientController {

    private final ClientService clientService;

    // CRUD -> role_user

    @PostMapping("/user/clients/new")
    public ClientDTO addClient(@RequestBody ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @GetMapping("/user/clients/{id}")
    public ClientDTO getUserById(@PathVariable Long id){
        return clientService.getClientById(id);
    }

    @GetMapping("/user/clients")
    public List<ClientDTO> getAllUsers(){
        return clientService.getAllClients();
    }

    @GetMapping("/user/clients/filter")
    public List<ClientDTO> getUsersByFilter(
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String date_of_birth) throws ParseException {
        try{
            return clientService.getClientsWithFilters(gender, city, country, date_of_birth);
        } catch (ParseException e){
            throw e;
        }


    }


    @DeleteMapping("/user/clients/{id}")
    public String deleteUser(@PathVariable Long id){
        return clientService.deleteClientById(id) ? "User supprimé" : "Erreur";
    }

    @PatchMapping("/user/clients/{id}")
    public ClientDTO patchUser(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        return clientService.modifyClient(clientDTO, id);
    }

    // SCRAPPING -> role_scrap

    @GetMapping("/scrapper/clients/new")
    public ClientDTO getNewUser(){
        return clientService.getNewClient();
    }

    @GetMapping("/scrapper/clients")
    public List<ClientDTO> getNewClientsScrapping(@RequestParam int number){
        return clientService.getNewClientScapping(number);
    }

    // REBOND DE REQUETE -> role_rebond

    @GetMapping("/rebond/clients")
    public ClientDTO rebondirClient(){
        return clientService.rebondirClient();
    }
}
