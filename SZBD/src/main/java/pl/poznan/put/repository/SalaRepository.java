package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.Sala;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.Budynek;

/**
 * = SalaRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Sala.class)
@Transactional
public interface SalaRepository extends DetachableJpaRepository<Sala, Long>, SalaRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @return Long
     */
    public abstract long countByBudynek(Budynek budynek);
}
