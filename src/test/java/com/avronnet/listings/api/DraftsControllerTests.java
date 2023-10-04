package com.avronnet.listings.api;

import com.avronnet.listings.persistance.models.Draft;
import com.avronnet.listings.persistance.repositories.DraftsRepository;
import com.avronnet.listings.services.DraftService;
import com.avronnet.listings.services.DraftServiceImpl;
import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class DraftsControllerTests {

    /**
     * Autowire in the service we want to test
     */
    @InjectMocks
    private DraftServiceImpl service;

    /**
     * Create a mock implementation of the DraftRepository
     */
    @Mock
    private DraftsRepository repository;

    @Test
    @DisplayName("Test findById Success")
    void testFindById() {
        // Set up our mock repository
        Draft draft = new Draft("randomID1", "Draft Name", "Description");
        doReturn(Optional.of(draft)).when(repository).findById("randomID1");

        // Execute the service call
        Optional<Draft> returnedDraft = service.findById("randomID1");

        // Assert the response
        Assert.isTrue(returnedDraft.isPresent(), "Draft was not found");
        Assertions.assertSame(returnedDraft.get(), draft, "The widget returned was not the same as the mock");
    }
}
