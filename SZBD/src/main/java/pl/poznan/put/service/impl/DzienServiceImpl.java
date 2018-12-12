package pl.poznan.put.service.impl;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.DzienService;
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
import pl.poznan.put.model.Dzien;
import pl.poznan.put.model.JednostkaLekcyjna;
import pl.poznan.put.repository.DzienRepository;
import pl.poznan.put.service.api.JednostkaLekcyjnaService;

/**
 * = DzienServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = DzienService.class)
@Service
@Transactional
public class DzienServiceImpl implements DzienService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private JednostkaLekcyjnaService jednostkaLekcyjnaService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private DzienRepository dzienRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param dzienRepository
     * @param jednostkaLekcyjnaService
     */
    @Autowired
    public DzienServiceImpl(DzienRepository dzienRepository, @Lazy JednostkaLekcyjnaService jednostkaLekcyjnaService) {
        setDzienRepository(dzienRepository);
        setJednostkaLekcyjnaService(jednostkaLekcyjnaService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return DzienRepository
     */
    public DzienRepository getDzienRepository() {
        return dzienRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzienRepository
     */
    public void setDzienRepository(DzienRepository dzienRepository) {
        this.dzienRepository = dzienRepository;
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
     * @param dzien
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Dzien dzien) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @param jednostkiLekcyjneToAdd
     * @return Dzien
     */
    @Transactional
    public Dzien addToJednostkiLekcyjne(Dzien dzien, Iterable<Long> jednostkiLekcyjneToAdd) {
        List<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjneToAdd);
        dzien.addToJednostkiLekcyjne(jednostkiLekcyjne);
        return getDzienRepository().save(dzien);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @param jednostkiLekcyjneToRemove
     * @return Dzien
     */
    @Transactional
    public Dzien removeFromJednostkiLekcyjne(Dzien dzien, Iterable<Long> jednostkiLekcyjneToRemove) {
        List<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjneToRemove);
        dzien.removeFromJednostkiLekcyjne(jednostkiLekcyjne);
        return getDzienRepository().save(dzien);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @param jednostkiLekcyjne
     * @return Dzien
     */
    @Transactional
    public Dzien setJednostkiLekcyjne(Dzien dzien, Iterable<Long> jednostkiLekcyjne) {
        List<JednostkaLekcyjna> items = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjne);
        Set<JednostkaLekcyjna> currents = dzien.getJednostkiLekcyjne();
        Set<JednostkaLekcyjna> toRemove = new HashSet<JednostkaLekcyjna>();
        for (Iterator<JednostkaLekcyjna> iterator = currents.iterator(); iterator.hasNext(); ) {
            JednostkaLekcyjna nextJednostkaLekcyjna = iterator.next();
            if (items.contains(nextJednostkaLekcyjna)) {
                items.remove(nextJednostkaLekcyjna);
            } else {
                toRemove.add(nextJednostkaLekcyjna);
            }
        }
        dzien.removeFromJednostkiLekcyjne(toRemove);
        dzien.addToJednostkiLekcyjne(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        dzien.setVersion(dzien.getVersion() + 1);
        return getDzienRepository().save(dzien);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     */
    @Transactional
    public void delete(Dzien dzien) {
        // Clear bidirectional one-to-many parent relationship with JednostkaLekcyjna
        for (JednostkaLekcyjna item : dzien.getJednostkiLekcyjne()) {
            item.setDzien(null);
        }
        getDzienRepository().delete(dzien);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Dzien> save(Iterable<Dzien> entities) {
        return getDzienRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Dzien> toDelete = getDzienRepository().findAll(ids);
        getDzienRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Dzien
     */
    @Transactional
    public Dzien save(Dzien entity) {
        return getDzienRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Dzien
     */
    public Dzien findOne(Long id) {
        return getDzienRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Dzien
     */
    public Dzien findOneForUpdate(Long id) {
        return getDzienRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Dzien> findAll(Iterable<Long> ids) {
        return getDzienRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Dzien> findAll() {
        return getDzienRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getDzienRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Dzien> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getDzienRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Dzien> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getDzienRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Dzien> getEntityType() {
        return Dzien.class;
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
