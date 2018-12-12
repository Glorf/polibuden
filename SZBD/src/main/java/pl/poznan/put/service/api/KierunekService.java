package pl.poznan.put.service.api;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import pl.poznan.put.model.Kierunek;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.poznan.put.model.Wydzial;

/**
 * = KierunekService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = Kierunek.class)
public interface KierunekService extends EntityResolver<Kierunek, Long>, ValidatorService<Kierunek> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Kierunek
     */
    public abstract Kierunek findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     */
    public abstract void delete(Kierunek kierunek);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Kierunek> save(Iterable<Kierunek> entities);

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
     * @return Kierunek
     */
    public abstract Kierunek save(Kierunek entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Kierunek
     */
    public abstract Kierunek findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Kierunek> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Kierunek> findAll();

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
    public abstract Page<Kierunek> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Kierunek> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param grupyToAdd
     * @return Kierunek
     */
    public abstract Kierunek addToGrupy(Kierunek kierunek, Iterable<Long> grupyToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param grupyToRemove
     * @return Kierunek
     */
    public abstract Kierunek removeFromGrupy(Kierunek kierunek, Iterable<Long> grupyToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param grupy
     * @return Kierunek
     */
    public abstract Kierunek setGrupy(Kierunek kierunek, Iterable<Long> grupy);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Kierunek> findByWydzial(Wydzial wydzial, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @return Long
     */
    public abstract long countByWydzial(Wydzial wydzial);
}
