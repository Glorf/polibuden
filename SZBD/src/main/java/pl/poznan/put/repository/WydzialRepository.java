package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.Wydzial;
import org.springframework.transaction.annotation.Transactional;

/**
 * = WydzialRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Wydzial.class)
@Transactional
public interface WydzialRepository extends DetachableJpaRepository<Wydzial, Long>, WydzialRepositoryCustom {
}
