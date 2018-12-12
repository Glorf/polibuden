package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.Wykladowca;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.Stopien;
import pl.poznan.put.model.Zespol;

/**
 * = WykladowcaRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Wykladowca.class)
@Transactional
public interface WykladowcaRepository extends DetachableJpaRepository<Wykladowca, Long>, WykladowcaRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @return Long
     */
    public abstract long countByZespol(Zespol zespol);

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @return Long
     */
    public abstract long countByStopien(Stopien stopien);
}
