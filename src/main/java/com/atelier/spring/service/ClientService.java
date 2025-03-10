package com.atelier.spring.service;


import com.atelier.spring.DTO.BeerDTO;
import com.atelier.spring.DTO.ClientDTO;
import com.atelier.spring.entity.Address;
import com.atelier.spring.entity.Client;
import com.atelier.spring.mapping.AddressMapping;
import com.atelier.spring.mapping.BeerMapping;
import com.atelier.spring.mapping.ClientMapping;
import com.atelier.spring.repository.AddressRepository;
import com.atelier.spring.repository.BeerRepository;
import com.atelier.spring.repository.ClientRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapping clientMapping;

    private final AddressRepository addressRepository;
    private final AddressMapping addressMapping;

    private static final String TARGET_HOST = "https://random-data-api.com/api/v2/users";
    private final WebClient webClient;

    public ClientService(WebClient.Builder webClientBuilder,
                       ClientRepository clientRepository,
                         ClientMapping clientMapping,
                         AddressRepository addressRepository,
                         AddressMapping addressMapping) {
        this.webClient = webClientBuilder.baseUrl(TARGET_HOST).build();
        this.clientRepository = clientRepository;
        this.clientMapping = clientMapping;
        this.addressRepository = addressRepository;
        this.addressMapping = addressMapping;
    }

    public ClientDTO getNewClient(){
        ClientDTO response = webClient
                .get()
                .uri("/")
                .retrieve()
                .bodyToMono(ClientDTO.class)
                .block();

        clientRepository.save(clientMapping.dtoToEntity(response));

        return response;
    }

    public ClientDTO getClientById(Long id){
        return clientMapping.entityToDTO(clientRepository.findById(id).get());
    }

    public List<ClientDTO> getAllClients(){
        Iterable<Client> clients = clientRepository.findAll();

        List<ClientDTO> response = new ArrayList<>();

        clients.forEach(client -> {
            response.add(clientMapping.entityToDTO(client));
        }
        );

        return response;
    }

    public List<ClientDTO> getClientsByGender(String gender){
        Iterable<Client> clients = clientRepository.findAllByGender(gender);
        List<ClientDTO> response = new ArrayList<>();
        clients.forEach(client -> {
            response.add(clientMapping.entityToDTO(client));
        });

        return response;
    }

    public List<ClientDTO> getClientsByCountry(String country){
        Iterable<Client> clients = clientRepository.findAllByCountry(country);
        List<ClientDTO> response = new ArrayList<>();
        clients.forEach(client -> {
            response.add(clientMapping.entityToDTO(client));
        });
        return response;
    }

    public List<ClientDTO> getClientsByCity(String city){
        Iterable<Client> clients = clientRepository.findAllByCity(city);
        List<ClientDTO> response = new ArrayList<>();
        clients.forEach(client -> {
            response.add(clientMapping.entityToDTO(client));
        });
        return response;
    }

    public List<ClientDTO> getClientsByDateofBirthBefore(Date date_of_birth){
        Iterable<Client> clients = clientRepository.findByDate_of_birthBefore(date_of_birth);
        List<ClientDTO> response = new ArrayList<>();
        clients.forEach(client -> {
            response.add(clientMapping.entityToDTO(client));
        });
        return response;
    }

    public List<ClientDTO> getClientsWithFilters(String gender, String city, String country, String date_of_birth) throws ParseException {

        try{
            List<ClientDTO> clients = new ArrayList<>();

            if (gender != null) {
                clients = getClientsByGender(gender);
            } else if (country != null) {
                clients = getClientsByCountry(country);
            } else if (city != null) {
                clients = getClientsByCity(city);
            } else if (date_of_birth != null) {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(date_of_birth);
                clients = getClientsByDateofBirthBefore(date);
            }

            return clients;
        } catch (ParseException e) {
            throw e;
        }

    }

    public boolean deleteClientById(Long id){
        try{
            clientRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public ClientDTO modifyClient(ClientDTO clientDTO, Long id){
        try{
            Client client = clientRepository.findById(id).get();
            client.setFirst_name(clientDTO.first_name());
            client.setLast_name(clientDTO.last_name());
            client.setUid(clientDTO.uid());
            client.setGender(clientDTO.gender());
            client.setPhone_number(clientDTO.phone_number());
            client.setEmail(clientDTO.email());
            client.setDate_of_birth(clientDTO.date_of_birth());
            client.setUsername(clientDTO.username());
            Address address = client.getAddress();
            address.setCity(clientDTO.address().city());
            address.setCountry(clientDTO.address().country());
            address.setZip_code(clientDTO.address().zip_code());
            clientRepository.save(client);
            addressRepository.save(address);
            return clientMapping.entityToDTO(client);
        } catch (Exception e){
            return null;
        }
    }

    public ClientDTO createClient(ClientDTO clientDTO){
        clientRepository.save(clientMapping.dtoToEntity(clientDTO));
        return clientDTO;
    }

    public List<ClientDTO> getNewClientScapping(int number){
        List<ClientDTO> clients = new ArrayList<>();

        List<ClientDTO> response = webClient
                .get()
                .uri( uriBuilder ->
                        uriBuilder.path("/")
                                .queryParam("size", number)
                                .build()

                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ClientDTO>>() {})
                .block();

        response.forEach(client -> {
            clientRepository.save(clientMapping.dtoToEntity(client));
            clients.add(client);
        });

        return clients;
    }

    public ClientDTO rebondirClient(){
        ClientDTO response = webClient
                .get()
                .uri("/")
                .retrieve()
                .bodyToMono(ClientDTO.class)
                .block();
        return response;
    }


}
