package pl.poznan.put.service.api;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import pl.poznan.put.model.Wykladowca;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.poznan.put.model.Stopien;
import pl.poznan.put.model.Zespol;

/**
 * = WykladowcaService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = Wykladowca.class)
public interface WykladowcaService extends EntityResolver<Wykladowca, Long>, ValidatorService<Wykladowca> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Wykladowca
     */
    public abstract Wykladowca findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowca
     */
    public abstract void delete(Wykladowca wykladowca);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Wykladowca> save(Iterable<Wykladowca> entities);

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
     * @return Wykladowca
     */
    public abstract Wykladowca save(Wykladowca entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Wykladowca
     */
    public abstract Wykladowca findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Wykladowca> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Wykladowca> findAll();

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

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowca
     * @param jednostkiLekcyjneToAdd
     * @return Wykladowca
     */
    public abstract Wykladowca addToJednostkiLekcyjne(Wykladowca wykladowca, Iterable<Long> jednostkiLekcyjneToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowca
     * @param jednostkiLekcyjneToRemove
     * @return Wykladowca
     */
    public abstract Wykladowca removeFromJednostkiLekcyjne(Wykladowca wykladowca, Iterable<Long> jednostkiLekcyjneToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowca
     * @param jednostkiLekcyjne
     * @return Wykladowca
     */
    public abstract Wykladowca setJednostkiLekcyjne(Wykladowca wykladowca, Iterable<Long> jednostkiLekcyjne);

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
     * @return Long
     */
    public abstract long countByStopien(Stopien stopien);

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @return Long
     */
    public abstract long countByZespol(Zespol zespol);
}