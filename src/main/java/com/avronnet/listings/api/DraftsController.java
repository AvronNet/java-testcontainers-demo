package com.avronnet.listings.api;

import com.avronnet.listings.persistance.models.Draft;
import com.avronnet.listings.persistance.repositories.DraftsRepository;
import com.avronnet.listings.services.DraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/drafts")
public class DraftsController {

    @Autowired
    private DraftService draftService;

    @GetMapping
    public Iterable<Draft> findAll() {
        return draftService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Draft> getDraftById(@PathVariable String id) {
        var schrodingerDraft = draftService.findById(id);
        return schrodingerDraft
                .map(listing -> new ResponseEntity<>(listing, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Draft createDraft(@RequestBody Draft draft) {
        return draftService.create(draft);
    }

    @PutMapping("/{id}")
    public Draft putDraft(@PathVariable String id, @RequestBody Draft draft) {
        Draft draftWithId = new Draft(id, draft.getTitle(), draft.getDescription());
        return draftService.put(draftWithId);
    }

    @PatchMapping("/{id}")
    public Draft updateDraft(@PathVariable String id, @RequestBody Draft draft) {
        Draft draftWithId = new Draft(id, draft.getTitle(), draft.getDescription());
        return draftService.update(draftWithId);
    }

    @DeleteMapping("/{id}")
    public void deleteDraft(@PathVariable String id) {
        draftService.deleteById(id);
    }
}
