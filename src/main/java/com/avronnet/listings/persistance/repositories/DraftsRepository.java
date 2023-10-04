package com.avronnet.listings.persistance.repositories;

import com.avronnet.listings.persistance.models.Draft;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DraftsRepository extends CrudRepository<Draft, String> {}