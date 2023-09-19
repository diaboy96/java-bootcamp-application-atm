package com.martindavidik.dataservice.repository;

import com.martindavidik.dataservice.domain.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
}
