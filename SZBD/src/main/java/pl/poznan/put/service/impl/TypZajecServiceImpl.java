package pl.poznan.put.service.impl;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.TypZajecService;
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
import pl.poznan.put.model.JednostkaLekcyjna;
import pl.poznan.put.model.TypZajec;
import pl.poznan.put.repository.TypZajecRepository;
import pl.poznan.put.service.api.JednostkaLekcyjnaService;

/**
 * = TypZajecServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = TypZajecService.class)
@Service
@Transactional
public class TypZajecServiceImpl implements TypZajecService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private TypZajecRepository typZajecRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private JednostkaLekcyjnaService jednostkaLekcyjnaService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param typZajecRepository
     * @param jednostkaLekcyjnaService
     */
    @Autowired
    public TypZajecServiceImpl(TypZajecRepository typZajecRepository, @Lazy JednostkaLekcyjnaService jednostkaLekcyjnaService) {
        setTypZajecRepository(typZajecRepository);
        setJednostkaLekcyjnaService(jednostkaLekcyjnaService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return TypZajecRepository
     */
    public TypZajecRepository getTypZajecRepository() {
        return typZajecRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajecRepository
     */
    public void setTypZajecRepository(TypZajecRepository typZajecRepository) {
        this.typZajecRepository = typZajecRepository;
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
     * @param typzajec
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(TypZajec typzajec) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajec
     * @param jednostkiLekcyjneToAdd
     * @return TypZajec
     */
    @Transactional
    public TypZajec addToJednostkiLekcyjne(TypZajec typZajec, Iterable<Long> jednostkiLekcyjneToAdd) {
        List<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjneToAdd);
        typZajec.addToJednostkiLekcyjne(jednostkiLekcyjne);
        return getTypZajecRepository().save(typZajec);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajec
     * @param jednostkiLekcyjneToRemove
     * @return TypZajec
     */
    @Transactional
    public TypZajec removeFromJednostkiLekcyjne(TypZajec typZajec, Iterable<Long> jednostkiLekcyjneToRemove) {
        List<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjneToRemove);
        typZajec.removeFromJednostkiLekcyjne(jednostkiLekcyjne);
        return getTypZajecRepository().save(typZajec);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajec
     * @param jednostkiLekcyjne
     * @return TypZajec
     */
    @Transactional
    public TypZajec setJednostkiLekcyjne(TypZajec typZajec, Iterable<Long> jednostkiLekcyjne) {
        List<JednostkaLekcyjna> items = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjne);
        Set<JednostkaLekcyjna> currents = typZajec.getJednostkiLekcyjne();
        Set<JednostkaLekcyjna> toRemove = new HashSet<JednostkaLekcyjna>();
        for (Iterator<JednostkaLekcyjna> iterator = currents.iterator(); iterator.hasNext(); ) {
            JednostkaLekcyjna nextJednostkaLekcyjna = iterator.next();
            if (items.contains(nextJednostkaLekcyjna)) {
                items.remove(nextJednostkaLekcyjna);
            } else {
                toRemove.add(nextJednostkaLekcyjna);
            }
        }
        typZajec.removeFromJednostkiLekcyjne(toRemove);
        typZajec.addToJednostkiLekcyjne(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        typZajec.setVersion(typZajec.getVersion() + 1);
        return getTypZajecRepository().save(typZajec);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajec
     */
    @Transactional
    public void delete(TypZajec typZajec) {
        // Clear bidirectional one-to-many parent relationship with JednostkaLekcyjna
        for (JednostkaLekcyjna item : typZajec.getJednostkiLekcyjne()) {
            item.setTyp(null);
        }
        getTypZajecRepository().delete(typZajec);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<TypZajec> save(Iterable<TypZajec> entities) {
        return getTypZajecRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<TypZajec> toDelete = getTypZajecRepository().findAll(ids);
        getTypZajecRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return TypZajec
     */
    @Transactional
    public TypZajec save(TypZajec entity) {
        return getTypZajecRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return TypZajec
     */
    public TypZajec findOne(Long id) {
        return getTypZajecRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return TypZajec
     */
    public TypZajec findOneForUpdate(Long id) {
        return getTypZajecRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<TypZajec> findAll(Iterable<Long> ids) {
        return getTypZajecRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<TypZajec> findAll() {
        return getTypZajecRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getTypZajecRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<TypZajec> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getTypZajecRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<TypZajec> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getTypZajecRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<TypZajec> getEntityType() {
        return TypZajec.class;
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
