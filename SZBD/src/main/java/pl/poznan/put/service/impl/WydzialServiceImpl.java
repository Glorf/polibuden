package pl.poznan.put.service.impl;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.WydzialService;
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
import pl.poznan.put.model.Kierunek;
import pl.poznan.put.model.Wydzial;
import pl.poznan.put.model.Zespol;
import pl.poznan.put.repository.WydzialRepository;
import pl.poznan.put.service.api.KierunekService;
import pl.poznan.put.service.api.ZespolService;


/**
 * = WydzialServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = WydzialService.class)
@Service
@Transactional
public class WydzialServiceImpl implements WydzialService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private KierunekService kierunekService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private WydzialRepository wydzialRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ZespolService zespolService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param wydzialRepository
     * @param kierunekService
     * @param zespolService
     */
    @Autowired
    public WydzialServiceImpl(WydzialRepository wydzialRepository, @Lazy KierunekService kierunekService, @Lazy ZespolService zespolService) {
        setWydzialRepository(wydzialRepository);
        setKierunekService(kierunekService);
        setZespolService(zespolService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return WydzialRepository
     */
    public WydzialRepository getWydzialRepository() {
        return wydzialRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzialRepository
     */
    public void setWydzialRepository(WydzialRepository wydzialRepository) {
        this.wydzialRepository = wydzialRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return KierunekService
     */
    public KierunekService getKierunekService() {
        return kierunekService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunekService
     */
    public void setKierunekService(KierunekService kierunekService) {
        this.kierunekService = kierunekService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ZespolService
     */
    public ZespolService getZespolService() {
        return zespolService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespolService
     */
    public void setZespolService(ZespolService zespolService) {
        this.zespolService = zespolService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Wydzial wydzial) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param kierunkiToAdd
     * @return Wydzial
     */
    @Transactional
    public Wydzial addToKierunki(Wydzial wydzial, Iterable<Long> kierunkiToAdd) {
        List<Kierunek> kierunki = getKierunekService().findAll(kierunkiToAdd);
        wydzial.addToKierunki(kierunki);
        return getWydzialRepository().save(wydzial);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param zespolyToAdd
     * @return Wydzial
     */
    @Transactional
    public Wydzial addToZespoly(Wydzial wydzial, Iterable<Long> zespolyToAdd) {
        List<Zespol> zespoly = getZespolService().findAll(zespolyToAdd);
        wydzial.addToZespoly(zespoly);
        return getWydzialRepository().save(wydzial);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param kierunkiToRemove
     * @return Wydzial
     */
    @Transactional
    public Wydzial removeFromKierunki(Wydzial wydzial, Iterable<Long> kierunkiToRemove) {
        List<Kierunek> kierunki = getKierunekService().findAll(kierunkiToRemove);
        wydzial.removeFromKierunki(kierunki);
        return getWydzialRepository().save(wydzial);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param zespolyToRemove
     * @return Wydzial
     */
    @Transactional
    public Wydzial removeFromZespoly(Wydzial wydzial, Iterable<Long> zespolyToRemove) {
        List<Zespol> zespoly = getZespolService().findAll(zespolyToRemove);
        wydzial.removeFromZespoly(zespoly);
        return getWydzialRepository().save(wydzial);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param kierunki
     * @return Wydzial
     */
    @Transactional
    public Wydzial setKierunki(Wydzial wydzial, Iterable<Long> kierunki) {
        List<Kierunek> items = getKierunekService().findAll(kierunki);
        Set<Kierunek> currents = wydzial.getKierunki();
        Set<Kierunek> toRemove = new HashSet<Kierunek>();
        for (Iterator<Kierunek> iterator = currents.iterator(); iterator.hasNext(); ) {
            Kierunek nextKierunek = iterator.next();
            if (items.contains(nextKierunek)) {
                items.remove(nextKierunek);
            } else {
                toRemove.add(nextKierunek);
            }
        }
        wydzial.removeFromKierunki(toRemove);
        wydzial.addToKierunki(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        wydzial.setVersion(wydzial.getVersion() + 1);
        return getWydzialRepository().save(wydzial);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param zespoly
     * @return Wydzial
     */
    @Transactional
    public Wydzial setZespoly(Wydzial wydzial, Iterable<Long> zespoly) {
        List<Zespol> items = getZespolService().findAll(zespoly);
        Set<Zespol> currents = wydzial.getZespoly();
        Set<Zespol> toRemove = new HashSet<Zespol>();
        for (Iterator<Zespol> iterator = currents.iterator(); iterator.hasNext(); ) {
            Zespol nextZespol = iterator.next();
            if (items.contains(nextZespol)) {
                items.remove(nextZespol);
            } else {
                toRemove.add(nextZespol);
            }
        }
        wydzial.removeFromZespoly(toRemove);
        wydzial.addToZespoly(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        wydzial.setVersion(wydzial.getVersion() + 1);
        return getWydzialRepository().save(wydzial);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     */
    @Transactional
    public void delete(Wydzial wydzial) {
        // Clear bidirectional one-to-many parent relationship with Kierunek
        for (Kierunek item : wydzial.getKierunki()) {
            item.setWydzial(null);
        }
        // Clear bidirectional one-to-many parent relationship with Zespol
        for (Zespol item : wydzial.getZespoly()) {
            item.setWydzial(null);
        }
        getWydzialRepository().delete(wydzial);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Wydzial> save(Iterable<Wydzial> entities){
            return getWydzialRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Wydzial> toDelete = getWydzialRepository().findAll(ids);
        getWydzialRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Wydzial
     */
    @Transactional
    public Wydzial save(Wydzial entity) {  
             return getWydzialRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Wydzial
     */
    public Wydzial findOne(Long id) {
        return getWydzialRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Wydzial
     */
    public Wydzial findOneForUpdate(Long id) {
        return getWydzialRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Wydzial> findAll(Iterable<Long> ids) {
        return getWydzialRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Wydzial> findAll() {
        return getWydzialRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getWydzialRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Wydzial> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getWydzialRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Wydzial> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getWydzialRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Wydzial> getEntityType() {
        return Wydzial.class;
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
