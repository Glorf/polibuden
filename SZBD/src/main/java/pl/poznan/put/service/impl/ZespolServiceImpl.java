package pl.poznan.put.service.impl;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.ZespolService;
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
import pl.poznan.put.model.Wydzial;
import pl.poznan.put.model.Wykladowca;
import pl.poznan.put.model.Zespol;
import pl.poznan.put.repository.ZespolRepository;
import pl.poznan.put.service.api.WykladowcaService;

/**
 * = ZespolServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = ZespolService.class)
@Service
@Transactional
public class ZespolServiceImpl implements ZespolService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private WykladowcaService wykladowcaService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ZespolRepository zespolRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param zespolRepository
     * @param wykladowcaService
     */
    @Autowired
    public ZespolServiceImpl(ZespolRepository zespolRepository, @Lazy WykladowcaService wykladowcaService) {
        setZespolRepository(zespolRepository);
        setWykladowcaService(wykladowcaService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ZespolRepository
     */
    public ZespolRepository getZespolRepository() {
        return zespolRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespolRepository
     */
    public void setZespolRepository(ZespolRepository zespolRepository) {
        this.zespolRepository = zespolRepository;
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
     * @param zespol
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Zespol zespol) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @param wykladowcyToAdd
     * @return Zespol
     */
    @Transactional
    public Zespol addToWykladowcy(Zespol zespol, Iterable<Long> wykladowcyToAdd) {
        List<Wykladowca> wykladowcy = getWykladowcaService().findAll(wykladowcyToAdd);
        zespol.addToWykladowcy(wykladowcy);
        return getZespolRepository().save(zespol);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @param wykladowcyToRemove
     * @return Zespol
     */
    @Transactional
    public Zespol removeFromWykladowcy(Zespol zespol, Iterable<Long> wykladowcyToRemove) {
        List<Wykladowca> wykladowcy = getWykladowcaService().findAll(wykladowcyToRemove);
        zespol.removeFromWykladowcy(wykladowcy);
        return getZespolRepository().save(zespol);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @param wykladowcy
     * @return Zespol
     */
    @Transactional
    public Zespol setWykladowcy(Zespol zespol, Iterable<Long> wykladowcy) {
        List<Wykladowca> items = getWykladowcaService().findAll(wykladowcy);
        Set<Wykladowca> currents = zespol.getWykladowcy();
        Set<Wykladowca> toRemove = new HashSet<Wykladowca>();
        for (Iterator<Wykladowca> iterator = currents.iterator(); iterator.hasNext(); ) {
            Wykladowca nextWykladowca = iterator.next();
            if (items.contains(nextWykladowca)) {
                items.remove(nextWykladowca);
            } else {
                toRemove.add(nextWykladowca);
            }
        }
        zespol.removeFromWykladowcy(toRemove);
        zespol.addToWykladowcy(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        zespol.setVersion(zespol.getVersion() + 1);
        return getZespolRepository().save(zespol);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     */
    @Transactional
    public void delete(Zespol zespol) {
        // Clear bidirectional many-to-one child relationship with Wydzial
        if (zespol.getWydzial() != null) {
            zespol.getWydzial().getZespoly().remove(zespol);
        }
        // Clear bidirectional one-to-many parent relationship with Wykladowca
        for (Wykladowca item : zespol.getWykladowcy()) {
            item.setZespol(null);
        }
        getZespolRepository().delete(zespol);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Zespol> save(Iterable<Zespol> entities) {
        return getZespolRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Zespol> toDelete = getZespolRepository().findAll(ids);
        getZespolRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Zespol
     */
    @Transactional
    public Zespol save(Zespol entity) {
        return getZespolRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Zespol
     */
    public Zespol findOne(Long id) {
        return getZespolRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Zespol
     */
    public Zespol findOneForUpdate(Long id) {
        return getZespolRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Zespol> findAll(Iterable<Long> ids) {
        return getZespolRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Zespol> findAll() {
        return getZespolRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getZespolRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Zespol> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getZespolRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Zespol> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getZespolRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Zespol> findByWydzial(Wydzial wydzial, GlobalSearch globalSearch, Pageable pageable) {
        return getZespolRepository().findByWydzial(wydzial, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @return Long
     */
    public long countByWydzial(Wydzial wydzial) {
        return getZespolRepository().countByWydzial(wydzial);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Zespol> getEntityType() {
        return Zespol.class;
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
