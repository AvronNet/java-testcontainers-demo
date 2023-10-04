package com.avronnet.listings.services;

import com.avronnet.listings.persistance.models.Listing;

import java.util.List;
import java.util.Optional;

public interface ListingService {

    Optional<Listing> findById(Long id);
    List<Listing> findByTitle(String title);
    List<Listing> findAll();
    Listing create(Listing draft);
    Listing put(Listing draft);
    Listing update(Listing draft);
    void deleteById(Long id);
}
