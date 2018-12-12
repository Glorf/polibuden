package pl.poznan.put.service.api;
import io.springlets.data.web.validation.ValidatorService;
import io.springlets.format.EntityResolver;
import org.postgresql.util.PSQLException;
import org.springframework.roo.addon.layers.service.annotations.RooService;
import pl.poznan.put.model.Budynek;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * = BudynekService
 *
 * TODO Auto-generated class documentation
 *
 */
@RooService(entity = Budynek.class)
public interface BudynekService extends EntityResolver<Budynek, Long>, ValidatorService<Budynek> {

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Budynek
     */
    public abstract Budynek findOne(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     */
    public abstract void delete(Budynek budynek);

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    public abstract List<Budynek> save(Iterable<Budynek> entities) throws PSQLException;

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
     * @return Budynek
     */
    public abstract Budynek save(Budynek entity) throws PSQLException;

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Budynek
     */
    public abstract Budynek findOneForUpdate(Long id);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public abstract List<Budynek> findAll(Iterable<Long> ids);

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public abstract List<Budynek> findAll();

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
    public abstract Page<Budynek> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Budynek> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param saleToAdd
     * @return Budynek
     */
    public abstract Budynek addToSale(Budynek budynek, Iterable<Long> saleToAdd);

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param saleToRemove
     * @return Budynek
     */
    public abstract Budynek removeFromSale(Budynek budynek, Iterable<Long> saleToRemove);

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param sale
     * @return Budynek
     */
    public abstract Budynek setSale(Budynek budynek, Iterable<Long> sale);
}
