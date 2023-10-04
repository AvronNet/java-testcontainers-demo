package com.avronnet.listings.services;

import com.avronnet.listings.persistance.models.Draft;
import com.avronnet.listings.persistance.repositories.DraftsRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DraftServiceImpl implements DraftService {
    private DraftsRepository draftsRepository;


    @Override
    public Optional<Draft> findById(String id) {
        return draftsRepository.findById(id);
    }

    @Override
    public List<Draft> findAll() {
        return Lists.newArrayList(draftsRepository.findAll());
    }

    @Override
    public Draft create(Draft draft) {
        return draftsRepository.save(draft);
    }

    @Override
    public Draft put(Draft draft) {
        var schrodingerDraft = draftsRepository.findById(draft.getId());
        if (schrodingerDraft.isPresent()) {
            draft.setId(draft.getId());
            return draftsRepository.save(draft);
        }
        return create(draft);
    }

    @Override
    public Draft update(Draft draft) {
        var schrodingerDraft = draftsRepository.findById(draft.getId());
        if (schrodingerDraft.isEmpty()) {
            throw new NoSuchElementException(String.format("Draft with ID %s doesn't exist!", draft.getId()));
        }

        var existingDraft = schrodingerDraft.get();
        if (draft.getTitle() != null && !draft.getTitle().trim().isEmpty()) {
            existingDraft.setTitle(draft.getTitle());
        }
        if (draft.getDescription() != null && !draft.getDescription().trim().isEmpty()) {
            existingDraft.setDescription(draft.getDescription());
        }
        return draftsRepository.save(existingDraft);
    }

    @Override
    public void deleteById(String id) {
        draftsRepository.deleteById(id);
    }
}
