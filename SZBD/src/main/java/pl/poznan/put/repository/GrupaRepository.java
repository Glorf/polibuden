package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.Grupa;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.Kierunek;

/**
 * = GrupaRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Grupa.class)
@Transactional
public interface GrupaRepository extends DetachableJpaRepository<Grupa, Long>, GrupaRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @return Long
     */
    public abstract long countByKierunek(Kierunek kierunek);
}
