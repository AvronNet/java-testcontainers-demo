package com.avronnet.listings.api;

import com.avronnet.listings.persistance.models.Listing;
import com.avronnet.listings.persistance.repositories.ListingsRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@RequestMapping("/api/listings")
public class ListingsController {

    @Autowired
    private ListingsRepository listingsRepository;

    @GetMapping
    public Iterable<Listing> findAll() {
        return listingsRepository.findAll();
    }

    @GetMapping("/title/{searchText}")
    public List<Listing> findByTitle(@PathVariable String searchText) {
        return listingsRepository.findByTitle(searchText);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable Long id) {
        var schrodingerListing = listingsRepository.findById(id);
        return schrodingerListing
                .map(listing -> new ResponseEntity<>(listing, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Listing createListing(@RequestBody Listing listing) {
        return listingsRepository.save(listing);
    }

    @PutMapping("/{id}")
    public Listing updateListing(@PathVariable Long id, @RequestBody Listing listing) {
        var schrodingerListing = listingsRepository.findById(id);
        if(schrodingerListing.isPresent()) {
            var existingListing = schrodingerListing.get();
            existingListing.setTitle(listing.getTitle());
            existingListing.setDescription(listing.getDescription());
            return listingsRepository.save(existingListing);
        }

        return createListing(listing);
    }
}
