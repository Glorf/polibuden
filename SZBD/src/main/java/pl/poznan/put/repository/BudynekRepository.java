package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.Budynek;
import org.springframework.transaction.annotation.Transactional;

/**
 * = BudynekRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Budynek.class)
@Transactional
public interface BudynekRepository extends DetachableJpaRepository<Budynek, Long>, BudynekRepositoryCustom {
}
