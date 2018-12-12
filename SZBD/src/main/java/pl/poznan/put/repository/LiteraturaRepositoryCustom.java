package pl.poznan.put.repository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustom;
import pl.poznan.put.model.Literatura;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.poznan.put.model.Przedmiot;

/**
 * = LiteraturaRepositoryCustom
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustom(entity = Literatura.class)
public interface LiteraturaRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmioty
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Literatura> findByPrzedmiotyContains(Przedmiot przedmioty, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Literatura> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Literatura> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
}
