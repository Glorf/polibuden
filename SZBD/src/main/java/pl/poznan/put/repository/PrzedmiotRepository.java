package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.Przedmiot;
import org.springframework.transaction.annotation.Transactional;

/**
 * = PrzedmiotRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Przedmiot.class)
@Transactional
public interface PrzedmiotRepository extends DetachableJpaRepository<Przedmiot, Long>, PrzedmiotRepositoryCustom {
}
