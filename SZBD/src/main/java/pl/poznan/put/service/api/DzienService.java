package pl.poznan.put.service.api;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import pl.poznan.put.model.Dzien;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = DzienService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = Dzien.class)
public interface DzienService extends EntityResolver<Dzien, Long>, ValidatorService<Dzien> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Dzien
     */
    public abstract Dzien findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     */
    public abstract void delete(Dzien dzien);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Dzien> save(Iterable<Dzien> entities);

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
     * @return Dzien
     */
    public abstract Dzien save(Dzien entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Dzien
     */
    public abstract Dzien findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Dzien> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Dzien> findAll();

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
    public abstract Page<Dzien> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Dzien> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @param jednostkiLekcyjneToAdd
     * @return Dzien
     */
    public abstract Dzien addToJednostkiLekcyjne(Dzien dzien, Iterable<Long> jednostkiLekcyjneToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @param jednostkiLekcyjneToRemove
     * @return Dzien
     */
    public abstract Dzien removeFromJednostkiLekcyjne(Dzien dzien, Iterable<Long> jednostkiLekcyjneToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @param jednostkiLekcyjne
     * @return Dzien
     */
    public abstract Dzien setJednostkiLekcyjne(Dzien dzien, Iterable<Long> jednostkiLekcyjne);
}
