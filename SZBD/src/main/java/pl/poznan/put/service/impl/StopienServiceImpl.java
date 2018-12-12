package pl.poznan.put.service.impl;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.StopienService;
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
import pl.poznan.put.model.Stopien;
import pl.poznan.put.model.Wykladowca;
import pl.poznan.put.repository.StopienRepository;
import pl.poznan.put.service.api.WykladowcaService;

/**
 * = StopienServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = StopienService.class)
@Service
@Transactional
public class StopienServiceImpl implements StopienService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private WykladowcaService wykladowcaService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private StopienRepository stopienRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param stopienRepository
     * @param wykladowcaService
     */
    @Autowired
    public StopienServiceImpl(StopienRepository stopienRepository, @Lazy WykladowcaService wykladowcaService) {
        setStopienRepository(stopienRepository);
        setWykladowcaService(wykladowcaService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return StopienRepository
     */
    public StopienRepository getStopienRepository() {
        return stopienRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopienRepository
     */
    public void setStopienRepository(StopienRepository stopienRepository) {
        this.stopienRepository = stopienRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return WykladowcaService
     */
    public WykladowcaService getWykladowcaService() {
        return wykladowcaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowcaService
     */
    public void setWykladowcaService(WykladowcaService wykladowcaService) {
        this.wykladowcaService = wykladowcaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Stopien stopien) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param wykladowcyToAdd
     * @return Stopien
     */
    @Transactional
    public Stopien addToWykladowcy(Stopien stopien, Iterable<Long> wykladowcyToAdd) {
        List<Wykladowca> wykladowcy = getWykladowcaService().findAll(wykladowcyToAdd);
        stopien.addToWykladowcy(wykladowcy);
        return getStopienRepository().save(stopien);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param wykladowcyToRemove
     * @return Stopien
     */
    @Transactional
    public Stopien removeFromWykladowcy(Stopien stopien, Iterable<Long> wykladowcyToRemove) {
        List<Wykladowca> wykladowcy = getWykladowcaService().findAll(wykladowcyToRemove);
        stopien.removeFromWykladowcy(wykladowcy);
        return getStopienRepository().save(stopien);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param wykladowcy
     * @return Stopien
     */
    @Transactional
    public Stopien setWykladowcy(Stopien stopien, Iterable<Long> wykladowcy) {
        List<Wykladowca> items = getWykladowcaService().findAll(wykladowcy);
        Set<Wykladowca> currents = stopien.getWykladowcy();
        Set<Wykladowca> toRemove = new HashSet<Wykladowca>();
        for (Iterator<Wykladowca> iterator = currents.iterator(); iterator.hasNext(); ) {
            Wykladowca nextWykladowca = iterator.next();
            if (items.contains(nextWykladowca)) {
                items.remove(nextWykladowca);
            } else {
                toRemove.add(nextWykladowca);
            }
        }
        stopien.removeFromWykladowcy(toRemove);
        stopien.addToWykladowcy(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        stopien.setVersion(stopien.getVersion() + 1);
        return getStopienRepository().save(stopien);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     */
    @Transactional
    public void delete(Stopien stopien) {
        // Clear bidirectional one-to-many parent relationship with Wykladowca
        for (Wykladowca item : stopien.getWykladowcy()) {
            item.setStopien(null);
        }
        getStopienRepository().delete(stopien);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Stopien> save(Iterable<Stopien> entities) {
        return getStopienRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Stopien> toDelete = getStopienRepository().findAll(ids);
        getStopienRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Stopien
     */
    @Transactional
    public Stopien save(Stopien entity) {
        return getStopienRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Stopien
     */
    public Stopien findOne(Long id) {
        return getStopienRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Stopien
     */
    public Stopien findOneForUpdate(Long id) {
        return getStopienRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Stopien> findAll(Iterable<Long> ids) {
        return getStopienRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Stopien> findAll() {
        return getStopienRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getStopienRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Stopien> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getStopienRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Stopien> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getStopienRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Stopien> getEntityType() {
        return Stopien.class;
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
