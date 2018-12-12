package pl.poznan.put.service.impl;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.WykladowcaService;
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
import pl.poznan.put.model.Stopien;
import pl.poznan.put.model.Wykladowca;
import pl.poznan.put.model.Zespol;
import pl.poznan.put.repository.WykladowcaRepository;
import pl.poznan.put.service.api.JednostkaLekcyjnaService;

import javax.persistence.EntityManager;

/**
 * = WykladowcaServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = WykladowcaService.class)
@Service
@Transactional
public class WykladowcaServiceImpl implements WykladowcaService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private WykladowcaRepository wykladowcaRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private JednostkaLekcyjnaService jednostkaLekcyjnaService;

    @Autowired
    private EntityManager em;
    /**
     * TODO Auto-generated constructor documentation
     *
     * @param wykladowcaRepository
     * @param jednostkaLekcyjnaService
     */
    @Autowired
    public WykladowcaServiceImpl(WykladowcaRepository wykladowcaRepository, @Lazy JednostkaLekcyjnaService jednostkaLekcyjnaService) {
        setWykladowcaRepository(wykladowcaRepository);
        setJednostkaLekcyjnaService(jednostkaLekcyjnaService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return WykladowcaRepository
     */
    public WykladowcaRepository getWykladowcaRepository() {
        return wykladowcaRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowcaRepository
     */
    public void setWykladowcaRepository(WykladowcaRepository wykladowcaRepository) {
        this.wykladowcaRepository = wykladowcaRepository;
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
     * @param wykladowca
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Wykladowca wykladowca) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowca
     * @param jednostkiLekcyjneToAdd
     * @return Wykladowca
     */
    @Transactional
    public Wykladowca addToJednostkiLekcyjne(Wykladowca wykladowca, Iterable<Long> jednostkiLekcyjneToAdd) {
        List<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjneToAdd);
        wykladowca.addToJednostkiLekcyjne(jednostkiLekcyjne);
        return getWykladowcaRepository().save(wykladowca);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowca
     * @param jednostkiLekcyjneToRemove
     * @return Wykladowca
     */
    @Transactional
    public Wykladowca removeFromJednostkiLekcyjne(Wykladowca wykladowca, Iterable<Long> jednostkiLekcyjneToRemove) {
        List<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjneToRemove);
        wykladowca.removeFromJednostkiLekcyjne(jednostkiLekcyjne);
        return getWykladowcaRepository().save(wykladowca);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowca
     * @param jednostkiLekcyjne
     * @return Wykladowca
     */
    @Transactional
    public Wykladowca setJednostkiLekcyjne(Wykladowca wykladowca, Iterable<Long> jednostkiLekcyjne) {
        List<JednostkaLekcyjna> items = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjne);
        Set<JednostkaLekcyjna> currents = wykladowca.getJednostkiLekcyjne();
        Set<JednostkaLekcyjna> toRemove = new HashSet<JednostkaLekcyjna>();
        for (Iterator<JednostkaLekcyjna> iterator = currents.iterator(); iterator.hasNext(); ) {
            JednostkaLekcyjna nextJednostkaLekcyjna = iterator.next();
            if (items.contains(nextJednostkaLekcyjna)) {
                items.remove(nextJednostkaLekcyjna);
            } else {
                toRemove.add(nextJednostkaLekcyjna);
            }
        }
        wykladowca.removeFromJednostkiLekcyjne(toRemove);
        wykladowca.addToJednostkiLekcyjne(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        wykladowca.setVersion(wykladowca.getVersion() + 1);
        return getWykladowcaRepository().save(wykladowca);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowca
     */
    @Transactional
    public void delete(Wykladowca wykladowca) {
        // Clear bidirectional many-to-one child relationship with Zespol
        if (wykladowca.getZespol() != null) {
            wykladowca.getZespol().getWykladowcy().remove(wykladowca);
        }
        // Clear bidirectional many-to-one child relationship with Stopien
        if (wykladowca.getStopien() != null) {
            wykladowca.getStopien().getWykladowcy().remove(wykladowca);
        }
        // Clear bidirectional one-to-many parent relationship with JednostkaLekcyjna
        for (JednostkaLekcyjna item : wykladowca.getJednostkiLekcyjne()) {
            item.setIdProwadzacy(null);
        }
        getWykladowcaRepository().delete(wykladowca);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Wykladowca> save(Iterable<Wykladowca> entities) {
        return getWykladowcaRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Wykladowca> toDelete = getWykladowcaRepository().findAll(ids);
        getWykladowcaRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Wykladowca
     */
    @Transactional
    public Wykladowca save(Wykladowca entity) {
        return getWykladowcaRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Wykladowca
     */
    public Wykladowca findOne(Long id) {
        return getWykladowcaRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Wykladowca
     */
    public Wykladowca findOneForUpdate(Long id) {
        return getWykladowcaRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Wykladowca> findAll(Iterable<Long> ids) {
        return getWykladowcaRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Wykladowca> findAll() {
        return getWykladowcaRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getWykladowcaRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Wykladowca> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getWykladowcaRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Wykladowca> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getWykladowcaRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Wykladowca> findByStopien(Stopien stopien, GlobalSearch globalSearch, Pageable pageable) {
        return getWykladowcaRepository().findByStopien(stopien, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Wykladowca> findByZespol(Zespol zespol, GlobalSearch globalSearch, Pageable pageable) {
        return getWykladowcaRepository().findByZespol(zespol, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @return Long
     */
    public long countByStopien(Stopien stopien) {
        return getWykladowcaRepository().countByStopien(stopien);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @return Long
     */
    public long countByZespol(Zespol zespol) {
        return getWykladowcaRepository().countByZespol(zespol);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Wykladowca> getEntityType() {
        return Wykladowca.class;
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
