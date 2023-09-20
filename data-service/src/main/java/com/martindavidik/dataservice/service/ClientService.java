package com.martindavidik.dataservice.service;

import com.martindavidik.dataservice.domain.Client;

public interface ClientService {

    Client save(Client client);

    void delete(Client client);
}
