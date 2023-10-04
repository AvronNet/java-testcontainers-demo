package com.avronnet.listings.services;

import com.avronnet.listings.persistance.models.Listing;
import com.avronnet.listings.persistance.repositories.ListingsRepository;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ListingServiceImpl implements ListingService {

    private ListingsRepository listingsRepository;

    @Override
    public Optional<Listing> findById(Long id) {
        return listingsRepository.findById(id);
    }

    @Override
    public List<Listing> findByTitle(String title) {
        return listingsRepository.findByTitle(title);
    }

    @Override
    public List<Listing> findAll() {
        return Lists.newArrayList(listingsRepository.findAll());
    }

    @Override
    public Listing create(Listing listing) {
        // validations go here
        return listingsRepository.save(listing);
    }

    @Override
    public Listing put(Listing listing) {
        // validations go here
        var schrodingerListing = listingsRepository.findById(listing.getId());
        if (schrodingerListing.isPresent()) {
            listing.setId(listing.getId());
            return listingsRepository.save(listing);
        }
        return create(listing);
    }

    @Override
    public Listing update(Listing listing) {
        var schrodingerListing = listingsRepository.findById(listing.getId());
        if (schrodingerListing.isEmpty()) {
            throw new NoSuchElementException(String.format("Listing with ID %s doesn't exist!", listing.getId()));
        }

        var existingListing = schrodingerListing.get();
        if (listing.getTitle() != null && !listing.getTitle().trim().isEmpty()) {
            existingListing.setTitle(listing.getTitle());
        }
        if (listing.getDescription() != null && !listing.getDescription().trim().isEmpty()) {
            existingListing.setDescription(listing.getDescription());
        }
        return listingsRepository.save(existingListing);
    }

    @Override
    public void deleteById(Long id) {
        listingsRepository.deleteById(id);
    }
}
