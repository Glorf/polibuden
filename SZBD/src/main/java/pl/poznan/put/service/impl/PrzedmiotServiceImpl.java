package pl.poznan.put.service.impl;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.PrzedmiotService;
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
import pl.poznan.put.model.Literatura;
import pl.poznan.put.model.Przedmiot;
import pl.poznan.put.repository.PrzedmiotRepository;
import pl.poznan.put.service.api.JednostkaLekcyjnaService;
import pl.poznan.put.service.api.LiteraturaService;

/**
 * = PrzedmiotServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = PrzedmiotService.class)
@Service
@Transactional
public class PrzedmiotServiceImpl implements PrzedmiotService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private LiteraturaService literaturaService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private PrzedmiotRepository przedmiotRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private JednostkaLekcyjnaService jednostkaLekcyjnaService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param przedmiotRepository
     * @param jednostkaLekcyjnaService
     * @param literaturaService
     */
    @Autowired
    public PrzedmiotServiceImpl(PrzedmiotRepository przedmiotRepository, @Lazy JednostkaLekcyjnaService jednostkaLekcyjnaService, @Lazy LiteraturaService literaturaService) {
        setPrzedmiotRepository(przedmiotRepository);
        setJednostkaLekcyjnaService(jednostkaLekcyjnaService);
        setLiteraturaService(literaturaService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return PrzedmiotRepository
     */
    public PrzedmiotRepository getPrzedmiotRepository() {
        return przedmiotRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiotRepository
     */
    public void setPrzedmiotRepository(PrzedmiotRepository przedmiotRepository) {
        this.przedmiotRepository = przedmiotRepository;
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
     * @return LiteraturaService
     */
    public LiteraturaService getLiteraturaService() {
        return literaturaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param literaturaService
     */
    public void setLiteraturaService(LiteraturaService literaturaService) {
        this.literaturaService = literaturaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Przedmiot przedmiot) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param jednostkiLekcyjneToAdd
     * @return Przedmiot
     */
    @Transactional
    public Przedmiot addToJednostkiLekcyjne(Przedmiot przedmiot, Iterable<Long> jednostkiLekcyjneToAdd) {
        List<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjneToAdd);
        przedmiot.addToJednostkiLekcyjne(jednostkiLekcyjne);
        return getPrzedmiotRepository().save(przedmiot);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param literaturaToAdd
     * @return Przedmiot
     */
    @Transactional
    public Przedmiot addToLiteratura(Przedmiot przedmiot, Iterable<Long> literaturaToAdd) {
        List<Literatura> literatura = getLiteraturaService().findAll(literaturaToAdd);
        przedmiot.addToLiteratura(literatura);
        return getPrzedmiotRepository().save(przedmiot);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param jednostkiLekcyjneToRemove
     * @return Przedmiot
     */
    @Transactional
    public Przedmiot removeFromJednostkiLekcyjne(Przedmiot przedmiot, Iterable<Long> jednostkiLekcyjneToRemove) {
        List<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjneToRemove);
        przedmiot.removeFromJednostkiLekcyjne(jednostkiLekcyjne);
        return getPrzedmiotRepository().save(przedmiot);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param literaturaToRemove
     * @return Przedmiot
     */
    @Transactional
    public Przedmiot removeFromLiteratura(Przedmiot przedmiot, Iterable<Long> literaturaToRemove) {
        List<Literatura> literatura = getLiteraturaService().findAll(literaturaToRemove);
        przedmiot.removeFromLiteratura(literatura);
        return getPrzedmiotRepository().save(przedmiot);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param jednostkiLekcyjne
     * @return Przedmiot
     */
    @Transactional
    public Przedmiot setJednostkiLekcyjne(Przedmiot przedmiot, Iterable<Long> jednostkiLekcyjne) {
        List<JednostkaLekcyjna> items = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjne);
        Set<JednostkaLekcyjna> currents = przedmiot.getJednostkiLekcyjne();
        Set<JednostkaLekcyjna> toRemove = new HashSet<JednostkaLekcyjna>();
        for (Iterator<JednostkaLekcyjna> iterator = currents.iterator(); iterator.hasNext(); ) {
            JednostkaLekcyjna nextJednostkaLekcyjna = iterator.next();
            if (items.contains(nextJednostkaLekcyjna)) {
                items.remove(nextJednostkaLekcyjna);
            } else {
                toRemove.add(nextJednostkaLekcyjna);
            }
        }
        przedmiot.removeFromJednostkiLekcyjne(toRemove);
        przedmiot.addToJednostkiLekcyjne(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        przedmiot.setVersion(przedmiot.getVersion() + 1);
        return getPrzedmiotRepository().save(przedmiot);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param literatura
     * @return Przedmiot
     */
    @Transactional
    public Przedmiot setLiteratura(Przedmiot przedmiot, Iterable<Long> literatura) {
        List<Literatura> items = getLiteraturaService().findAll(literatura);
        Set<Literatura> currents = przedmiot.getLiteratura();
        Set<Literatura> toRemove = new HashSet<Literatura>();
        for (Iterator<Literatura> iterator = currents.iterator(); iterator.hasNext(); ) {
            Literatura nextLiteratura = iterator.next();
            if (items.contains(nextLiteratura)) {
                items.remove(nextLiteratura);
            } else {
                toRemove.add(nextLiteratura);
            }
        }
        przedmiot.removeFromLiteratura(toRemove);
        przedmiot.addToLiteratura(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        przedmiot.setVersion(przedmiot.getVersion() + 1);
        return getPrzedmiotRepository().save(przedmiot);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     */
    @Transactional
    public void delete(Przedmiot przedmiot) {
        // Clear bidirectional one-to-many parent relationship with JednostkaLekcyjna
        for (JednostkaLekcyjna item : przedmiot.getJednostkiLekcyjne()) {
            item.setIdPrzedmiotu(null);
        }
        // Clear bidirectional many-to-many parent relationship with Literatura
        for (Literatura item : przedmiot.getLiteratura()) {
            item.getPrzedmioty().remove(przedmiot);
        }
        getPrzedmiotRepository().delete(przedmiot);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Przedmiot> save(Iterable<Przedmiot> entities) {
        return getPrzedmiotRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Przedmiot> toDelete = getPrzedmiotRepository().findAll(ids);
        getPrzedmiotRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Przedmiot
     */
    @Transactional
    public Przedmiot save(Przedmiot entity) {
        return getPrzedmiotRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Przedmiot
     */
    public Przedmiot findOne(Long id) {
        return getPrzedmiotRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Przedmiot
     */
    public Przedmiot findOneForUpdate(Long id) {
        return getPrzedmiotRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Przedmiot> findAll(Iterable<Long> ids) {
        return getPrzedmiotRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Przedmiot> findAll() {
        return getPrzedmiotRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getPrzedmiotRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Przedmiot> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getPrzedmiotRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Przedmiot> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getPrzedmiotRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Przedmiot> getEntityType() {
        return Przedmiot.class;
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
