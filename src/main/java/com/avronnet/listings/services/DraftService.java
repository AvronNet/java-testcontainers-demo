package com.avronnet.listings.services;

import com.avronnet.listings.persistance.models.Draft;

import java.util.List;
import java.util.Optional;

public interface DraftService {
    Optional<Draft> findById(String id);
    List<Draft> findAll();
    Draft create(Draft draft);
    Draft put(Draft draft);
    Draft update(Draft draft);
    void deleteById(String id);
}
