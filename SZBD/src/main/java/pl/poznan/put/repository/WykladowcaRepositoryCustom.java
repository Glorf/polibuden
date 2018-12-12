package pl.poznan.put.repository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustom;
import pl.poznan.put.model.Wykladowca;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.poznan.put.model.Stopien;
import pl.poznan.put.model.Zespol;

/**
 * = WykladowcaRepositoryCustom
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustom(entity = Wykladowca.class)
public interface WykladowcaRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Wykladowca> findByZespol(Zespol zespol, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Wykladowca> findByStopien(Stopien stopien, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Wykladowca> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Wykladowca> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
}
