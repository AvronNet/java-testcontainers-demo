package com.avronnet.listings.api;

import com.avronnet.listings.persistance.models.Listing;
import com.avronnet.listings.services.ListingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@RequestMapping("/api/listings")
public class ListingsController {

    @Autowired
    private ListingService listingService;

    @GetMapping
    public Iterable<Listing> findAll() {
        return listingService.findAll();
    }

    @GetMapping("/title/{searchText}")
    public List<Listing> findByTitle(@PathVariable String searchText) {
        return listingService.findByTitle(searchText);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListingById(@PathVariable Long id) {
        var schrodingerListing = listingService.findById(id);
        return schrodingerListing
                .map(listing -> new ResponseEntity<>(listing, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Listing> createListing(@RequestBody Listing listing) {
        return new ResponseEntity<>(listingService.create(listing), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Listing> updateListing(@PathVariable Long id, @RequestBody Listing listing) {
        var ListingWithId = new Listing(id, listing.getTitle(), listing.getDescription());
        return ResponseEntity.ok(listingService.put(ListingWithId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Listing> patchListing(@PathVariable Long id, @RequestBody Listing listing) {
        var ListingWithId = new Listing(id, listing.getTitle(), listing.getDescription());
        return ResponseEntity.ok(listingService.update(ListingWithId));
    }

    @DeleteMapping("/{id}")
    public void deleteListing(@PathVariable Long id) {
        listingService.deleteById(id);
    }
}
