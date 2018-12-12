package pl.poznan.put.repository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustom;
import pl.poznan.put.model.Sala;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.poznan.put.model.Budynek;

/**
 * = SalaRepositoryCustom
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustom(entity = Sala.class)
public interface SalaRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Sala> findByBudynek(Budynek budynek, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Sala> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Sala> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
}
