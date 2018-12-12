package pl.poznan.put.service.api;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import pl.poznan.put.model.Sala;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.poznan.put.model.Budynek;

/**
 * = SalaService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = Sala.class)
public interface SalaService extends EntityResolver<Sala, Long>, ValidatorService<Sala> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Sala
     */
    public abstract Sala findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param sala
     */
    public abstract void delete(Sala sala);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Sala> save(Iterable<Sala> entities);

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
     * @return Sala
     */
    public abstract Sala save(Sala entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Sala
     */
    public abstract Sala findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Sala> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Sala> findAll();

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

    /**
     * TODO Auto-generated method documentation
     *
     * @param sala
     * @param jednostkiLekcyjneToAdd
     * @return Sala
     */
    public abstract Sala addToJednostkiLekcyjne(Sala sala, Iterable<Long> jednostkiLekcyjneToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param sala
     * @param jednostkiLekcyjneToRemove
     * @return Sala
     */
    public abstract Sala removeFromJednostkiLekcyjne(Sala sala, Iterable<Long> jednostkiLekcyjneToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param sala
     * @param jednostkiLekcyjne
     * @return Sala
     */
    public abstract Sala setJednostkiLekcyjne(Sala sala, Iterable<Long> jednostkiLekcyjne);

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
     * @param budynek
     * @return Long
     */
    public abstract long countByBudynek(Budynek budynek);
}
