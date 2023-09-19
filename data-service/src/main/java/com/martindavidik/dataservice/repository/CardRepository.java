package com.martindavidik.dataservice.repository;

import com.martindavidik.dataservice.domain.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Integer> {
}
