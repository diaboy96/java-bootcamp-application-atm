package com.martindavidik.dataservice.service.impl;

import com.martindavidik.dataservice.domain.Client;
import com.martindavidik.dataservice.repository.ClientRepository;
import com.martindavidik.dataservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }
}
