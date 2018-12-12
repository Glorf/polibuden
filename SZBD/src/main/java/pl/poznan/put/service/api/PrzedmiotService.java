package pl.poznan.put.service.api;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import pl.poznan.put.model.Przedmiot;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = PrzedmiotService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = Przedmiot.class)
public interface PrzedmiotService extends EntityResolver<Przedmiot, Long>, ValidatorService<Przedmiot> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Przedmiot
     */
    public abstract Przedmiot findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     */
    public abstract void delete(Przedmiot przedmiot);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Przedmiot> save(Iterable<Przedmiot> entities);

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
     * @return Przedmiot
     */
    public abstract Przedmiot save(Przedmiot entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Przedmiot
     */
    public abstract Przedmiot findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Przedmiot> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Przedmiot> findAll();

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
    public abstract Page<Przedmiot> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Przedmiot> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param jednostkiLekcyjneToAdd
     * @return Przedmiot
     */
    public abstract Przedmiot addToJednostkiLekcyjne(Przedmiot przedmiot, Iterable<Long> jednostkiLekcyjneToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param jednostkiLekcyjneToRemove
     * @return Przedmiot
     */
    public abstract Przedmiot removeFromJednostkiLekcyjne(Przedmiot przedmiot, Iterable<Long> jednostkiLekcyjneToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param jednostkiLekcyjne
     * @return Przedmiot
     */
    public abstract Przedmiot setJednostkiLekcyjne(Przedmiot przedmiot, Iterable<Long> jednostkiLekcyjne);

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param literaturaToAdd
     * @return Przedmiot
     */
    public abstract Przedmiot addToLiteratura(Przedmiot przedmiot, Iterable<Long> literaturaToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param literaturaToRemove
     * @return Przedmiot
     */
    public abstract Przedmiot removeFromLiteratura(Przedmiot przedmiot, Iterable<Long> literaturaToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param literatura
     * @return Przedmiot
     */
    public abstract Przedmiot setLiteratura(Przedmiot przedmiot, Iterable<Long> literatura);
}
