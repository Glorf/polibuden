package pl.poznan.put.service.impl;
import org.postgresql.util.PSQLException;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.BudynekService;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.validation.MessageI18n;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.Budynek;
import pl.poznan.put.model.Sala;
import pl.poznan.put.repository.BudynekRepository;
import pl.poznan.put.service.api.SalaService;

/**
 * = BudynekServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = BudynekService.class)
@Service
@Transactional
public class BudynekServiceImpl implements BudynekService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private BudynekRepository budynekRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private SalaService salaService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param budynekRepository
     * @param salaService
     */
    @Autowired
    public BudynekServiceImpl(BudynekRepository budynekRepository, @Lazy SalaService salaService) {
        setBudynekRepository(budynekRepository);
        setSalaService(salaService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return BudynekRepository
     */
    public BudynekRepository getBudynekRepository() {
        return budynekRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynekRepository
     */
    public void setBudynekRepository(BudynekRepository budynekRepository) {
        this.budynekRepository = budynekRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return SalaService
     */
    public SalaService getSalaService() {
        return salaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param salaService
     */
    public void setSalaService(SalaService salaService) {
        this.salaService = salaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Budynek budynek) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param saleToAdd
     * @return Budynek
     */
    @Transactional
    public Budynek addToSale(Budynek budynek, Iterable<Long> saleToAdd) {
        List<Sala> sale = getSalaService().findAll(saleToAdd);
        budynek.addToSale(sale);
        return getBudynekRepository().save(budynek);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param saleToRemove
     * @return Budynek
     */
    @Transactional
    public Budynek removeFromSale(Budynek budynek, Iterable<Long> saleToRemove) {
        List<Sala> sale = getSalaService().findAll(saleToRemove);
        budynek.removeFromSale(sale);
        return getBudynekRepository().save(budynek);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param sale
     * @return Budynek
     */
    @Transactional
    public Budynek setSale(Budynek budynek, Iterable<Long> sale) {
        List<Sala> items = getSalaService().findAll(sale);
        Set<Sala> currents = budynek.getSale();
        Set<Sala> toRemove = new HashSet<Sala>();
        for (Iterator<Sala> iterator = currents.iterator(); iterator.hasNext(); ) {
            Sala nextSala = iterator.next();
            if (items.contains(nextSala)) {
                items.remove(nextSala);
            } else {
                toRemove.add(nextSala);
            }
        }
        budynek.removeFromSale(toRemove);
        budynek.addToSale(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        budynek.setVersion(budynek.getVersion() + 1);
        return getBudynekRepository().save(budynek);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     */
    @Transactional
    public void delete(Budynek budynek) {
        // Clear bidirectional one-to-many parent relationship with Sala
        for (Sala item : budynek.getSale()) {
            item.setBudynek(null);
        }
        getBudynekRepository().delete(budynek);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Budynek> save(Iterable<Budynek> entities)throws PSQLException {
        return getBudynekRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Budynek> toDelete = getBudynekRepository().findAll(ids);
        getBudynekRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Budynek
     */
    @Transactional
    public Budynek save(Budynek entity) throws PSQLException {
        return getBudynekRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Budynek
     */
    public Budynek findOne(Long id) {
        return getBudynekRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Budynek
     */
    public Budynek findOneForUpdate(Long id) {
        return getBudynekRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Budynek> findAll(Iterable<Long> ids) {
        return getBudynekRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Budynek> findAll() {
        return getBudynekRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getBudynekRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Budynek> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getBudynekRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Budynek> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getBudynekRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Budynek> getEntityType() {
        return Budynek.class;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Long> getIdType() {
        return Long.class;
    }
}
