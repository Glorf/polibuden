package pl.poznan.put.service.impl;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.SalaService;
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
import pl.poznan.put.model.JednostkaLekcyjna;
import pl.poznan.put.model.Sala;
import pl.poznan.put.repository.SalaRepository;
import pl.poznan.put.service.api.JednostkaLekcyjnaService;

/**
 * = SalaServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = SalaService.class)
@Service
@Transactional
public class SalaServiceImpl implements SalaService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private SalaRepository salaRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private JednostkaLekcyjnaService jednostkaLekcyjnaService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param salaRepository
     * @param jednostkaLekcyjnaService
     */
    @Autowired
    public SalaServiceImpl(SalaRepository salaRepository, @Lazy JednostkaLekcyjnaService jednostkaLekcyjnaService) {
        setSalaRepository(salaRepository);
        setJednostkaLekcyjnaService(jednostkaLekcyjnaService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return SalaRepository
     */
    public SalaRepository getSalaRepository() {
        return salaRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param salaRepository
     */
    public void setSalaRepository(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return JednostkaLekcyjnaService
     */
    public JednostkaLekcyjnaService getJednostkaLekcyjnaService() {
        return jednostkaLekcyjnaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkaLekcyjnaService
     */
    public void setJednostkaLekcyjnaService(JednostkaLekcyjnaService jednostkaLekcyjnaService) {
        this.jednostkaLekcyjnaService = jednostkaLekcyjnaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sala
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Sala sala) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sala
     * @param jednostkiLekcyjneToAdd
     * @return Sala
     */
    @Transactional
    public Sala addToJednostkiLekcyjne(Sala sala, Iterable<Long> jednostkiLekcyjneToAdd) {
        List<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjneToAdd);
        sala.addToJednostkiLekcyjne(jednostkiLekcyjne);
        return getSalaRepository().save(sala);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sala
     * @param jednostkiLekcyjneToRemove
     * @return Sala
     */
    @Transactional
    public Sala removeFromJednostkiLekcyjne(Sala sala, Iterable<Long> jednostkiLekcyjneToRemove) {
        List<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjneToRemove);
        sala.removeFromJednostkiLekcyjne(jednostkiLekcyjne);
        return getSalaRepository().save(sala);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sala
     * @param jednostkiLekcyjne
     * @return Sala
     */
    @Transactional
    public Sala setJednostkiLekcyjne(Sala sala, Iterable<Long> jednostkiLekcyjne) {
        List<JednostkaLekcyjna> items = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjne);
        Set<JednostkaLekcyjna> currents = sala.getJednostkiLekcyjne();
        Set<JednostkaLekcyjna> toRemove = new HashSet<JednostkaLekcyjna>();
        for (Iterator<JednostkaLekcyjna> iterator = currents.iterator(); iterator.hasNext(); ) {
            JednostkaLekcyjna nextJednostkaLekcyjna = iterator.next();
            if (items.contains(nextJednostkaLekcyjna)) {
                items.remove(nextJednostkaLekcyjna);
            } else {
                toRemove.add(nextJednostkaLekcyjna);
            }
        }
        sala.removeFromJednostkiLekcyjne(toRemove);
        sala.addToJednostkiLekcyjne(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        sala.setVersion(sala.getVersion() + 1);
        return getSalaRepository().save(sala);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param sala
     */
    @Transactional
    public void delete(Sala sala) {
        // Clear bidirectional many-to-one child relationship with Budynek
        if (sala.getBudynek() != null) {
            sala.getBudynek().getSale().remove(sala);
        }
        // Clear bidirectional one-to-many parent relationship with JednostkaLekcyjna
        for (JednostkaLekcyjna item : sala.getJednostkiLekcyjne()) {
            item.setIdSali(null);
        }
        getSalaRepository().delete(sala);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Sala> save(Iterable<Sala> entities) {
        return getSalaRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Sala> toDelete = getSalaRepository().findAll(ids);
        getSalaRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Sala
     */
    @Transactional
    public Sala save(Sala entity) {
        return getSalaRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Sala
     */
    public Sala findOne(Long id) {
        return getSalaRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Sala
     */
    public Sala findOneForUpdate(Long id) {
        return getSalaRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Sala> findAll(Iterable<Long> ids) {
        return getSalaRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Sala> findAll() {
        return getSalaRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getSalaRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Sala> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getSalaRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Sala> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getSalaRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Sala> findByBudynek(Budynek budynek, GlobalSearch globalSearch, Pageable pageable) {
        return getSalaRepository().findByBudynek(budynek, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @return Long
     */
    public long countByBudynek(Budynek budynek) {
        return getSalaRepository().countByBudynek(budynek);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Sala> getEntityType() {
        return Sala.class;
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
