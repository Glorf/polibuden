package pl.poznan.put.service.impl;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.LiteraturaService;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.validation.MessageI18n;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.Literatura;
import pl.poznan.put.model.Przedmiot;
import pl.poznan.put.repository.LiteraturaRepository;

/**
 * = LiteraturaServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = LiteraturaService.class)
@Service
@Transactional
public class LiteraturaServiceImpl implements LiteraturaService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private LiteraturaRepository literaturaRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param literaturaRepository
     */
    @Autowired
    public LiteraturaServiceImpl(LiteraturaRepository literaturaRepository) {
        setLiteraturaRepository(literaturaRepository);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return LiteraturaRepository
     */
    public LiteraturaRepository getLiteraturaRepository() {
        return literaturaRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param literaturaRepository
     */
    public void setLiteraturaRepository(LiteraturaRepository literaturaRepository) {
        this.literaturaRepository = literaturaRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param literatura
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Literatura literatura) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param literatura
     */
    @Transactional
    public void delete(Literatura literatura) {
        // Clear bidirectional many-to-many child relationship with Przedmiot
        for (Przedmiot item : literatura.getPrzedmioty()) {
            item.getLiteratura().remove(literatura);
        }
        getLiteraturaRepository().delete(literatura);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Literatura> save(Iterable<Literatura> entities) {
        return getLiteraturaRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Literatura> toDelete = getLiteraturaRepository().findAll(ids);
        getLiteraturaRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Literatura
     */
    @Transactional
    public Literatura save(Literatura entity) {
        return getLiteraturaRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Literatura
     */
    public Literatura findOne(Long id) {
        return getLiteraturaRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Literatura
     */
    public Literatura findOneForUpdate(Long id) {
        return getLiteraturaRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Literatura> findAll(Iterable<Long> ids) {
        return getLiteraturaRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Literatura> findAll() {
        return getLiteraturaRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getLiteraturaRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Literatura> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getLiteraturaRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Literatura> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getLiteraturaRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmioty
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Literatura> findByPrzedmiotyContains(Przedmiot przedmioty, GlobalSearch globalSearch, Pageable pageable) {
        return getLiteraturaRepository().findByPrzedmiotyContains(przedmioty, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmioty
     * @return Long
     */
    public long countByPrzedmiotyContains(Przedmiot przedmioty) {
        return getLiteraturaRepository().countByPrzedmiotyContains(przedmioty);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Literatura> getEntityType() {
        return Literatura.class;
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
