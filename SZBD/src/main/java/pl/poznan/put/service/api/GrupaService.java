package pl.poznan.put.service.api;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import pl.poznan.put.model.Grupa;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.poznan.put.model.Kierunek;

/**
 * = GrupaService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = Grupa.class)
public interface GrupaService extends EntityResolver<Grupa, Long>, ValidatorService<Grupa> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Grupa
     */
    public abstract Grupa findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     */
    public abstract void delete(Grupa grupa);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Grupa> save(Iterable<Grupa> entities);

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
     * @return Grupa
     */
    public abstract Grupa save(Grupa entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Grupa
     */
    public abstract Grupa findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Grupa> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Grupa> findAll();

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

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param jednostkiLekcyjneToAdd
     * @return Grupa
     */
    public abstract Grupa addToJednostkiLekcyjne(Grupa grupa, Iterable<Long> jednostkiLekcyjneToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param jednostkiLekcyjneToRemove
     * @return Grupa
     */
    public abstract Grupa removeFromJednostkiLekcyjne(Grupa grupa, Iterable<Long> jednostkiLekcyjneToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param jednostkiLekcyjne
     * @return Grupa
     */
    public abstract Grupa setJednostkiLekcyjne(Grupa grupa, Iterable<Long> jednostkiLekcyjne);

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param studenciToAdd
     * @return Grupa
     */
    public abstract Grupa addToStudenci(Grupa grupa, Iterable<Long> studenciToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param studenciToRemove
     * @return Grupa
     */
    public abstract Grupa removeFromStudenci(Grupa grupa, Iterable<Long> studenciToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param studenci
     * @return Grupa
     */
    public abstract Grupa setStudenci(Grupa grupa, Iterable<Long> studenci);

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
     * @param kierunek
     * @return Long
     */
    public abstract long countByKierunek(Kierunek kierunek);
}
