package pl.poznan.put.repository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustom;
import pl.poznan.put.model.Grupa;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.poznan.put.model.Kierunek;

/**
 * = GrupaRepositoryCustom
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustom(entity = Grupa.class)
public interface GrupaRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Grupa> findByKierunek(Kierunek kierunek, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Grupa> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Grupa> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
}
