package com.avronnet.listings.persistance.repositories;

import com.avronnet.listings.persistance.models.Listing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingsRepository extends CrudRepository<Listing, Long> {
        List<Listing> findByTitle(String title);
}
