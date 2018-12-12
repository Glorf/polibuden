package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.Kierunek;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.Wydzial;

/**
 * = KierunekRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Kierunek.class)
@Transactional
public interface KierunekRepository extends DetachableJpaRepository<Kierunek, Long>, KierunekRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @return Long
     */
    public abstract long countByWydzial(Wydzial wydzial);
}
