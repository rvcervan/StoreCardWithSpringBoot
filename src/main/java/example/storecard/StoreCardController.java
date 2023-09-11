package example.storecard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/storecards")
@CrossOrigin
public class StoreCardController {

    @Autowired
    private StoreCardRepository storeCardRepository;

    public StoreCardController(StoreCardRepository storeCardRepository){
        this.storeCardRepository = storeCardRepository;
    }

    private StoreCard findStoreCard(Long requestedId, Principal principal){
        return storeCardRepository.findByIdAndOwner(requestedId, principal.getName());
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<StoreCard> findById(@PathVariable Long requestedId, Principal principal){
        StoreCard storeCard = findStoreCard(requestedId, principal);
        if(storeCard != null){
            return ResponseEntity.ok(storeCard);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity createStoreCard(@RequestBody StoreCard newStoreCardRequest, UriComponentsBuilder ubc, Principal principal){
        StoreCard storeCardWithOwner = new StoreCard(null, newStoreCardRequest.getAmount(), principal.getName());

        StoreCard savedStoreCard = storeCardRepository.save(storeCardWithOwner);

        URI locationOfNewStoreCard = ubc
                .path("storecards/{id}")
                .buildAndExpand(savedStoreCard.getId())
                .toUri();

        return ResponseEntity.created(locationOfNewStoreCard).build();
    }

    @GetMapping
    public ResponseEntity<Collection<StoreCard>> findAll(Pageable pageable, Principal principal){
            Page<StoreCard> page = storeCardRepository.findByOwner(principal.getName(),
                    PageRequest.of(
                            pageable.getPageNumber(),
                            pageable.getPageSize(),
                            pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
                    )
            );
            return ResponseEntity.ok(page.toList());
    }

    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> putStoreCard(@PathVariable Long requestedId, @RequestBody StoreCard storeCardUpdate, Principal principal){
        StoreCard storeCard = findStoreCard(requestedId, principal);

        if(storeCard != null){
            StoreCard updatedStoreCard = new StoreCard(storeCard.getId(), storeCardUpdate.getAmount(), principal.getName());
            storeCardRepository.save(updatedStoreCard);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Void> deleteStoreCard(@PathVariable Long id, Principal principal){
        if(storeCardRepository.existsByIdAndOwner(id, principal.getName())){
            storeCardRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
