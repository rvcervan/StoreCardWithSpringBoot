package example.storecard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface StoreCardRepository extends JpaRepository<StoreCard, Long>, PagingAndSortingRepository<StoreCard, Long> {
    boolean existsByIdAndOwner(Long id, String owner);

    StoreCard findByIdAndOwner(Long id, String owner);

    Page<StoreCard> findByOwner(String owner, PageRequest amount);

}
