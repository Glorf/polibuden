package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.TypZajec;
import org.springframework.transaction.annotation.Transactional;

/**
 * = TypZajecRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = TypZajec.class)
@Transactional
public interface TypZajecRepository extends DetachableJpaRepository<TypZajec, Long>, TypZajecRepositoryCustom {
}
