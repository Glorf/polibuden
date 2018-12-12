package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.Dzien;
import org.springframework.transaction.annotation.Transactional;

/**
 * = DzienRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Dzien.class)
@Transactional
public interface DzienRepository extends DetachableJpaRepository<Dzien, Long>, DzienRepositoryCustom {
}
