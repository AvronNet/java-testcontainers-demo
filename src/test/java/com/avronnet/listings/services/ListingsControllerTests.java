package com.avronnet.listings.services;

import com.avronnet.listings.persistance.models.Listing;
import com.avronnet.listings.persistance.models.Listing;
import com.avronnet.listings.persistance.repositories.ListingsRepository;
import com.avronnet.listings.persistance.repositories.ListingsRepository;
import com.avronnet.listings.services.ListingServiceImpl;
import com.avronnet.listings.services.ListingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ListingsControllerTests {
    /**
     * Autowire in the service we want to test
     */
    @InjectMocks
    private ListingServiceImpl service;

    /**
     * Create a mock implementation of the ListingRepository
     */
    @Mock
    private ListingsRepository repository;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        // Set up our mock repository
        Listing listing = new Listing(1L, "Listing Name", "Description");
        doReturn(Optional.of(listing)).when(repository).findById(1L);

        // Execute the service call
        Optional<Listing> returnedListing = service.findById(1L);

        // Assert the response
        Assert.isTrue(returnedListing.isPresent(), "Listing was not found");
        Assertions.assertSame(returnedListing.get(), listing, "The widget returned was not the same as the mock");
    }
}
