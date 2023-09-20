package com.martindavidik.dataservice.service;

import com.martindavidik.dataservice.domain.Client;

public interface ClientService {

    /**
     * Saves client entity to database
     *
     * @param client - entity to be saved
     *
     * @return Client entity
     */
    Client save(Client client);

    /**
     * Deletes client entity from database
     *
     * @param client - entity to be deleted
     */
    void delete(Client client);
}
