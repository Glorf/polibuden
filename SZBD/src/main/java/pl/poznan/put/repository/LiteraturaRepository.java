package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.Literatura;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.Przedmiot;

/**
 * = LiteraturaRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Literatura.class)
@Transactional
public interface LiteraturaRepository extends DetachableJpaRepository<Literatura, Long>, LiteraturaRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmioty
     * @return Long
     */
    public abstract long countByPrzedmiotyContains(Przedmiot przedmioty);
}
