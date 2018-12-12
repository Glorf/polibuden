package pl.poznan.put.service.impl;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.KierunekService;
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
import pl.poznan.put.model.Grupa;
import pl.poznan.put.model.Kierunek;
import pl.poznan.put.model.Wydzial;
import pl.poznan.put.repository.KierunekRepository;
import pl.poznan.put.service.api.GrupaService;

/**
 * = KierunekServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = KierunekService.class)
@Service
@Transactional
public class KierunekServiceImpl implements KierunekService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private KierunekRepository kierunekRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private GrupaService grupaService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param kierunekRepository
     * @param grupaService
     */
    @Autowired
    public KierunekServiceImpl(KierunekRepository kierunekRepository, @Lazy GrupaService grupaService) {
        setKierunekRepository(kierunekRepository);
        setGrupaService(grupaService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return KierunekRepository
     */
    public KierunekRepository getKierunekRepository() {
        return kierunekRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunekRepository
     */
    public void setKierunekRepository(KierunekRepository kierunekRepository) {
        this.kierunekRepository = kierunekRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return GrupaService
     */
    public GrupaService getGrupaService() {
        return grupaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupaService
     */
    public void setGrupaService(GrupaService grupaService) {
        this.grupaService = grupaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Kierunek kierunek) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param grupyToAdd
     * @return Kierunek
     */
    @Transactional
    public Kierunek addToGrupy(Kierunek kierunek, Iterable<Long> grupyToAdd) {
        List<Grupa> grupy = getGrupaService().findAll(grupyToAdd);
        kierunek.addToGrupy(grupy);
        return getKierunekRepository().save(kierunek);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param grupyToRemove
     * @return Kierunek
     */
    @Transactional
    public Kierunek removeFromGrupy(Kierunek kierunek, Iterable<Long> grupyToRemove) {
        List<Grupa> grupy = getGrupaService().findAll(grupyToRemove);
        kierunek.removeFromGrupy(grupy);
        return getKierunekRepository().save(kierunek);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param grupy
     * @return Kierunek
     */
    @Transactional
    public Kierunek setGrupy(Kierunek kierunek, Iterable<Long> grupy) {
        List<Grupa> items = getGrupaService().findAll(grupy);
        Set<Grupa> currents = kierunek.getGrupy();
        Set<Grupa> toRemove = new HashSet<Grupa>();
        for (Iterator<Grupa> iterator = currents.iterator(); iterator.hasNext(); ) {
            Grupa nextGrupa = iterator.next();
            if (items.contains(nextGrupa)) {
                items.remove(nextGrupa);
            } else {
                toRemove.add(nextGrupa);
            }
        }
        kierunek.removeFromGrupy(toRemove);
        kierunek.addToGrupy(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        kierunek.setVersion(kierunek.getVersion() + 1);
        return getKierunekRepository().save(kierunek);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     */
    @Transactional
    public void delete(Kierunek kierunek) {
        // Clear bidirectional many-to-one child relationship with Wydzial
        if (kierunek.getWydzial() != null) {
            kierunek.getWydzial().getKierunki().remove(kierunek);
        }
        // Clear bidirectional one-to-many parent relationship with Grupa
        for (Grupa item : kierunek.getGrupy()) {
            item.setKierunek(null);
        }
        getKierunekRepository().delete(kierunek);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Kierunek> save(Iterable<Kierunek> entities) {
        return getKierunekRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Kierunek> toDelete = getKierunekRepository().findAll(ids);
        getKierunekRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Kierunek
     */
    @Transactional
    public Kierunek save(Kierunek entity) {
        return getKierunekRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Kierunek
     */
    public Kierunek findOne(Long id) {
        return getKierunekRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Kierunek
     */
    public Kierunek findOneForUpdate(Long id) {
        return getKierunekRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Kierunek> findAll(Iterable<Long> ids) {
        return getKierunekRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Kierunek> findAll() {
        return getKierunekRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getKierunekRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Kierunek> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getKierunekRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Kierunek> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getKierunekRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Kierunek> findByWydzial(Wydzial wydzial, GlobalSearch globalSearch, Pageable pageable) {
        return getKierunekRepository().findByWydzial(wydzial, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @return Long
     */
    public long countByWydzial(Wydzial wydzial) {
        return getKierunekRepository().countByWydzial(wydzial);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Kierunek> getEntityType() {
        return Kierunek.class;
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
