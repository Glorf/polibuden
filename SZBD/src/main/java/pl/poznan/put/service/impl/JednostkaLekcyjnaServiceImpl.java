package pl.poznan.put.service.impl;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.JednostkaLekcyjnaService;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.validation.MessageI18n;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.Dzien;
import pl.poznan.put.model.Grupa;
import pl.poznan.put.model.JednostkaLekcyjna;
import pl.poznan.put.model.Przedmiot;
import pl.poznan.put.model.Sala;
import pl.poznan.put.model.TypZajec;
import pl.poznan.put.model.Wykladowca;
import pl.poznan.put.repository.JednostkaLekcyjnaRepository;

/**
 * = JednostkaLekcyjnaServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = JednostkaLekcyjnaService.class)
@Service
@Transactional
public class JednostkaLekcyjnaServiceImpl implements JednostkaLekcyjnaService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private JednostkaLekcyjnaRepository jednostkaLekcyjnaRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param jednostkaLekcyjnaRepository
     */
    @Autowired
    public JednostkaLekcyjnaServiceImpl(JednostkaLekcyjnaRepository jednostkaLekcyjnaRepository) {
        setJednostkaLekcyjnaRepository(jednostkaLekcyjnaRepository);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return JednostkaLekcyjnaRepository
     */
    public JednostkaLekcyjnaRepository getJednostkaLekcyjnaRepository() {
        return jednostkaLekcyjnaRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkaLekcyjnaRepository
     */
    public void setJednostkaLekcyjnaRepository(JednostkaLekcyjnaRepository jednostkaLekcyjnaRepository) {
        this.jednostkaLekcyjnaRepository = jednostkaLekcyjnaRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkalekcyjna
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(JednostkaLekcyjna jednostkalekcyjna) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkaLekcyjna
     */
    @Transactional
    public void delete(JednostkaLekcyjna jednostkaLekcyjna) {
        // Clear bidirectional many-to-one child relationship with Wykladowca
        if (jednostkaLekcyjna.getIdProwadzacy() != null) {
            jednostkaLekcyjna.getIdProwadzacy().getJednostkiLekcyjne().remove(jednostkaLekcyjna);
        }
        // Clear bidirectional many-to-one child relationship with Przedmiot
        if (jednostkaLekcyjna.getIdPrzedmiotu() != null) {
            jednostkaLekcyjna.getIdPrzedmiotu().getJednostkiLekcyjne().remove(jednostkaLekcyjna);
        }
        // Clear bidirectional many-to-one child relationship with Sala
        if (jednostkaLekcyjna.getIdSali() != null) {
            jednostkaLekcyjna.getIdSali().getJednostkiLekcyjne().remove(jednostkaLekcyjna);
        }
        // Clear bidirectional many-to-one child relationship with Dzien
        if (jednostkaLekcyjna.getDzien() != null) {
            jednostkaLekcyjna.getDzien().getJednostkiLekcyjne().remove(jednostkaLekcyjna);
        }
        // Clear bidirectional many-to-many child relationship with Grupa
        for (Grupa item : jednostkaLekcyjna.getGrupas()) {
            item.getJednostkiLekcyjne().remove(jednostkaLekcyjna);
        }
        // Clear bidirectional many-to-one child relationship with TypZajec
        if (jednostkaLekcyjna.getTyp() != null) {
            jednostkaLekcyjna.getTyp().getJednostkiLekcyjne().remove(jednostkaLekcyjna);
        }
        getJednostkaLekcyjnaRepository().delete(jednostkaLekcyjna);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<JednostkaLekcyjna> save(Iterable<JednostkaLekcyjna> entities) {
        return getJednostkaLekcyjnaRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<JednostkaLekcyjna> toDelete = getJednostkaLekcyjnaRepository().findAll(ids);
        getJednostkaLekcyjnaRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return JednostkaLekcyjna
     */
    @Transactional
    public JednostkaLekcyjna save(JednostkaLekcyjna entity) {
        return getJednostkaLekcyjnaRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return JednostkaLekcyjna
     */
    public JednostkaLekcyjna findOne(Long id) {
        return getJednostkaLekcyjnaRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return JednostkaLekcyjna
     */
    public JednostkaLekcyjna findOneForUpdate(Long id) {
        return getJednostkaLekcyjnaRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<JednostkaLekcyjna> findAll(Iterable<Long> ids) {
        return getJednostkaLekcyjnaRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<JednostkaLekcyjna> findAll() {
        return getJednostkaLekcyjnaRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getJednostkaLekcyjnaRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<JednostkaLekcyjna> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getJednostkaLekcyjnaRepository().findAll(globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<JednostkaLekcyjna> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getJednostkaLekcyjnaRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<JednostkaLekcyjna> findByDzien(Dzien dzien, GlobalSearch globalSearch, Pageable pageable) {
        return getJednostkaLekcyjnaRepository().findByDzien(dzien, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupas
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<JednostkaLekcyjna> findByGrupasContains(Grupa grupas, GlobalSearch globalSearch, Pageable pageable) {
        return getJednostkaLekcyjnaRepository().findByGrupasContains(grupas, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param idProwadzacy
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<JednostkaLekcyjna> findByIdProwadzacy(Wykladowca idProwadzacy, GlobalSearch globalSearch, Pageable pageable) {
        return getJednostkaLekcyjnaRepository().findByIdProwadzacy(idProwadzacy, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param idPrzedmiotu
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<JednostkaLekcyjna> findByIdPrzedmiotu(Przedmiot idPrzedmiotu, GlobalSearch globalSearch, Pageable pageable) {
        return getJednostkaLekcyjnaRepository().findByIdPrzedmiotu(idPrzedmiotu, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param idSali
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<JednostkaLekcyjna> findByIdSali(Sala idSali, GlobalSearch globalSearch, Pageable pageable) {
        return getJednostkaLekcyjnaRepository().findByIdSali(idSali, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typ
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<JednostkaLekcyjna> findByTyp(TypZajec typ, GlobalSearch globalSearch, Pageable pageable) {
        return getJednostkaLekcyjnaRepository().findByTyp(typ, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @return Long
     */
    public long countByDzien(Dzien dzien) {
        return getJednostkaLekcyjnaRepository().countByDzien(dzien);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupas
     * @return Long
     */
    public long countByGrupasContains(Grupa grupas) {
        return getJednostkaLekcyjnaRepository().countByGrupasContains(grupas);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param idProwadzacy
     * @return Long
     */
    public long countByIdProwadzacy(Wykladowca idProwadzacy) {
        return getJednostkaLekcyjnaRepository().countByIdProwadzacy(idProwadzacy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param idPrzedmiotu
     * @return Long
     */
    public long countByIdPrzedmiotu(Przedmiot idPrzedmiotu) {
        return getJednostkaLekcyjnaRepository().countByIdPrzedmiotu(idPrzedmiotu);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param idSali
     * @return Long
     */
    public long countByIdSali(Sala idSali) {
        return getJednostkaLekcyjnaRepository().countByIdSali(idSali);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typ
     * @return Long
     */
    public long countByTyp(TypZajec typ) {
        return getJednostkaLekcyjnaRepository().countByTyp(typ);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<JednostkaLekcyjna> getEntityType() {
        return JednostkaLekcyjna.class;
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
