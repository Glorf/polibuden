package pl.poznan.put.repository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustom;
import pl.poznan.put.model.Zespol;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.poznan.put.model.Wydzial;

/**
 * = ZespolRepositoryCustom
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustom(entity = Zespol.class)
public interface ZespolRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Zespol> findByWydzial(Wydzial wydzial, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Zespol> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Zespol> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
}
