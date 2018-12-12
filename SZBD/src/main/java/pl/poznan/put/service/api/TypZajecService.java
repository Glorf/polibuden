package pl.poznan.put.service.api;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import pl.poznan.put.model.TypZajec;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = TypZajecService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = TypZajec.class)
public interface TypZajecService extends EntityResolver<TypZajec, Long>, ValidatorService<TypZajec> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return TypZajec
     */
    public abstract TypZajec findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajec
     */
    public abstract void delete(TypZajec typZajec);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<TypZajec> save(Iterable<TypZajec> entities);

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
     * @return TypZajec
     */
    public abstract TypZajec save(TypZajec entity);

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return TypZajec
     */
    public abstract TypZajec findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<TypZajec> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<TypZajec> findAll();

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
    public abstract Page<TypZajec> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<TypZajec> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajec
     * @param jednostkiLekcyjneToAdd
     * @return TypZajec
     */
    public abstract TypZajec addToJednostkiLekcyjne(TypZajec typZajec, Iterable<Long> jednostkiLekcyjneToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajec
     * @param jednostkiLekcyjneToRemove
     * @return TypZajec
     */
    public abstract TypZajec removeFromJednostkiLekcyjne(TypZajec typZajec, Iterable<Long> jednostkiLekcyjneToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajec
     * @param jednostkiLekcyjne
     * @return TypZajec
     */
    public abstract TypZajec setJednostkiLekcyjne(TypZajec typZajec, Iterable<Long> jednostkiLekcyjne);
}
