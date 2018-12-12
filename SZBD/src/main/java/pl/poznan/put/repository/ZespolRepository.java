package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.Zespol;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.Wydzial;

/**
 * = ZespolRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Zespol.class)
@Transactional
public interface ZespolRepository extends DetachableJpaRepository<Zespol, Long>, ZespolRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @return Long
     */
    public abstract long countByWydzial(Wydzial wydzial);
}
