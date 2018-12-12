package pl.poznan.put.service.api;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import pl.poznan.put.model.Literatura;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.poznan.put.model.Przedmiot;

/**
 * = LiteraturaService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = Literatura.class)
public interface LiteraturaService extends EntityResolver<Literatura, Long>, ValidatorService<Literatura> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Literatura
     */
    public abstract Literatura findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param literatura
     */
    public abstract void delete(Literatura literatura);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Literatura> save(Iterable<Literatura> entities);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    public abstract void delete(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Literatura
     */
    public abstract Literatura save(Literatura entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Literatura
     */
    public abstract Literatura findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Literatura> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Literatura> findAll();

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public abstract long count();

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
     * @param przedmioty
     * @return Long
     */
    public abstract long countByPrzedmiotyContains(Przedmiot przedmioty);
}
