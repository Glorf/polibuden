package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.Stopien;
import org.springframework.transaction.annotation.Transactional;

/**
 * = StopienRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Stopien.class)
@Transactional
public interface StopienRepository extends DetachableJpaRepository<Stopien, Long>, StopienRepositoryCustom {
}
