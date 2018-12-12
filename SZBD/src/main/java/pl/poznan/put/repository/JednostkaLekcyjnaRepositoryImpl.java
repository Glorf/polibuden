package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.JednostkaLekcyjna;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pl.poznan.put.model.Dzien;
import pl.poznan.put.model.Grupa;
import pl.poznan.put.model.Przedmiot;
import pl.poznan.put.model.QJednostkaLekcyjna;
import pl.poznan.put.model.Sala;
import pl.poznan.put.model.TypZajec;
import pl.poznan.put.model.Wykladowca;

/**
 * = JednostkaLekcyjnaRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = JednostkaLekcyjnaRepositoryCustom.class)
@Transactional
public class JednostkaLekcyjnaRepositoryImpl extends QueryDslRepositorySupportExt<JednostkaLekcyjna> implements JednostkaLekcyjnaRepositoryCustom {

    /**
     * Default constructor
     */
    JednostkaLekcyjnaRepositoryImpl() {
        super(JednostkaLekcyjna.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String DZIEN = "dzien";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String GODZINA_DO = "godzinaDo";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ID_PROWADZACY = "idProwadzacy";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String TYP = "typ";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ID_PRZEDMIOTU = "idPrzedmiotu";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ID_SALI = "idSali";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String GODZINA_OD = "godzinaOd";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<JednostkaLekcyjna> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QJednostkaLekcyjna jednostkaLekcyjna = QJednostkaLekcyjna.jednostkaLekcyjna;
        JPQLQuery<JednostkaLekcyjna> query = from(jednostkaLekcyjna);
        Path<?>[] paths = new Path<?>[] { jednostkaLekcyjna.idSali, jednostkaLekcyjna.idProwadzacy, jednostkaLekcyjna.godzinaOd, jednostkaLekcyjna.godzinaDo, jednostkaLekcyjna.idPrzedmiotu, jednostkaLekcyjna.typ, jednostkaLekcyjna.dzien };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ID_SALI, jednostkaLekcyjna.idSali).map(ID_PROWADZACY, jednostkaLekcyjna.idProwadzacy).map(GODZINA_OD, jednostkaLekcyjna.godzinaOd).map(GODZINA_DO, jednostkaLekcyjna.godzinaDo).map(ID_PRZEDMIOTU, jednostkaLekcyjna.idPrzedmiotu).map(TYP, jednostkaLekcyjna.typ).map(DZIEN, jednostkaLekcyjna.dzien);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, jednostkaLekcyjna);
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
        QJednostkaLekcyjna jednostkaLekcyjna = QJednostkaLekcyjna.jednostkaLekcyjna;
        JPQLQuery<JednostkaLekcyjna> query = from(jednostkaLekcyjna);
        Path<?>[] paths = new Path<?>[] { jednostkaLekcyjna.idSali, jednostkaLekcyjna.idProwadzacy, jednostkaLekcyjna.godzinaOd, jednostkaLekcyjna.godzinaDo, jednostkaLekcyjna.idPrzedmiotu, jednostkaLekcyjna.typ, jednostkaLekcyjna.dzien };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(jednostkaLekcyjna.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(ID_SALI, jednostkaLekcyjna.idSali).map(ID_PROWADZACY, jednostkaLekcyjna.idProwadzacy).map(GODZINA_OD, jednostkaLekcyjna.godzinaOd).map(GODZINA_DO, jednostkaLekcyjna.godzinaDo).map(ID_PRZEDMIOTU, jednostkaLekcyjna.idPrzedmiotu).map(TYP, jednostkaLekcyjna.typ).map(DZIEN, jednostkaLekcyjna.dzien);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, jednostkaLekcyjna);
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
        QJednostkaLekcyjna jednostkaLekcyjna = QJednostkaLekcyjna.jednostkaLekcyjna;
        JPQLQuery<JednostkaLekcyjna> query = from(jednostkaLekcyjna);
        Assert.notNull(dzien, "dzien is required");
        query.where(jednostkaLekcyjna.dzien.eq(dzien));
        Path<?>[] paths = new Path<?>[] { jednostkaLekcyjna.idSali, jednostkaLekcyjna.idProwadzacy, jednostkaLekcyjna.godzinaOd, jednostkaLekcyjna.godzinaDo, jednostkaLekcyjna.idPrzedmiotu, jednostkaLekcyjna.typ, jednostkaLekcyjna.dzien };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ID_SALI, jednostkaLekcyjna.idSali).map(ID_PROWADZACY, jednostkaLekcyjna.idProwadzacy).map(GODZINA_OD, jednostkaLekcyjna.godzinaOd).map(GODZINA_DO, jednostkaLekcyjna.godzinaDo).map(ID_PRZEDMIOTU, jednostkaLekcyjna.idPrzedmiotu).map(TYP, jednostkaLekcyjna.typ).map(DZIEN, jednostkaLekcyjna.dzien);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, jednostkaLekcyjna);
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
        QJednostkaLekcyjna jednostkaLekcyjna = QJednostkaLekcyjna.jednostkaLekcyjna;
        JPQLQuery<JednostkaLekcyjna> query = from(jednostkaLekcyjna);
        Assert.notNull(grupas, "grupas is required");
        query.where(jednostkaLekcyjna.grupas.contains(grupas));
        Path<?>[] paths = new Path<?>[] { jednostkaLekcyjna.idSali, jednostkaLekcyjna.idProwadzacy, jednostkaLekcyjna.godzinaOd, jednostkaLekcyjna.godzinaDo, jednostkaLekcyjna.idPrzedmiotu, jednostkaLekcyjna.typ, jednostkaLekcyjna.dzien };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ID_SALI, jednostkaLekcyjna.idSali).map(ID_PROWADZACY, jednostkaLekcyjna.idProwadzacy).map(GODZINA_OD, jednostkaLekcyjna.godzinaOd).map(GODZINA_DO, jednostkaLekcyjna.godzinaDo).map(ID_PRZEDMIOTU, jednostkaLekcyjna.idPrzedmiotu).map(TYP, jednostkaLekcyjna.typ).map(DZIEN, jednostkaLekcyjna.dzien);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, jednostkaLekcyjna);
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
        QJednostkaLekcyjna jednostkaLekcyjna = QJednostkaLekcyjna.jednostkaLekcyjna;
        JPQLQuery<JednostkaLekcyjna> query = from(jednostkaLekcyjna);
        Assert.notNull(idProwadzacy, "idProwadzacy is required");
        query.where(jednostkaLekcyjna.idProwadzacy.eq(idProwadzacy));
        Path<?>[] paths = new Path<?>[] { jednostkaLekcyjna.idSali, jednostkaLekcyjna.idProwadzacy, jednostkaLekcyjna.godzinaOd, jednostkaLekcyjna.godzinaDo, jednostkaLekcyjna.idPrzedmiotu, jednostkaLekcyjna.typ, jednostkaLekcyjna.dzien };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ID_SALI, jednostkaLekcyjna.idSali).map(ID_PROWADZACY, jednostkaLekcyjna.idProwadzacy).map(GODZINA_OD, jednostkaLekcyjna.godzinaOd).map(GODZINA_DO, jednostkaLekcyjna.godzinaDo).map(ID_PRZEDMIOTU, jednostkaLekcyjna.idPrzedmiotu).map(TYP, jednostkaLekcyjna.typ).map(DZIEN, jednostkaLekcyjna.dzien);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, jednostkaLekcyjna);
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
        QJednostkaLekcyjna jednostkaLekcyjna = QJednostkaLekcyjna.jednostkaLekcyjna;
        JPQLQuery<JednostkaLekcyjna> query = from(jednostkaLekcyjna);
        Assert.notNull(idPrzedmiotu, "idPrzedmiotu is required");
        query.where(jednostkaLekcyjna.idPrzedmiotu.eq(idPrzedmiotu));
        Path<?>[] paths = new Path<?>[] { jednostkaLekcyjna.idSali, jednostkaLekcyjna.idProwadzacy, jednostkaLekcyjna.godzinaOd, jednostkaLekcyjna.godzinaDo, jednostkaLekcyjna.idPrzedmiotu, jednostkaLekcyjna.typ, jednostkaLekcyjna.dzien };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ID_SALI, jednostkaLekcyjna.idSali).map(ID_PROWADZACY, jednostkaLekcyjna.idProwadzacy).map(GODZINA_OD, jednostkaLekcyjna.godzinaOd).map(GODZINA_DO, jednostkaLekcyjna.godzinaDo).map(ID_PRZEDMIOTU, jednostkaLekcyjna.idPrzedmiotu).map(TYP, jednostkaLekcyjna.typ).map(DZIEN, jednostkaLekcyjna.dzien);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, jednostkaLekcyjna);
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
        QJednostkaLekcyjna jednostkaLekcyjna = QJednostkaLekcyjna.jednostkaLekcyjna;
        JPQLQuery<JednostkaLekcyjna> query = from(jednostkaLekcyjna);
        Assert.notNull(idSali, "idSali is required");
        query.where(jednostkaLekcyjna.idSali.eq(idSali));
        Path<?>[] paths = new Path<?>[] { jednostkaLekcyjna.idSali, jednostkaLekcyjna.idProwadzacy, jednostkaLekcyjna.godzinaOd, jednostkaLekcyjna.godzinaDo, jednostkaLekcyjna.idPrzedmiotu, jednostkaLekcyjna.typ, jednostkaLekcyjna.dzien };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ID_SALI, jednostkaLekcyjna.idSali).map(ID_PROWADZACY, jednostkaLekcyjna.idProwadzacy).map(GODZINA_OD, jednostkaLekcyjna.godzinaOd).map(GODZINA_DO, jednostkaLekcyjna.godzinaDo).map(ID_PRZEDMIOTU, jednostkaLekcyjna.idPrzedmiotu).map(TYP, jednostkaLekcyjna.typ).map(DZIEN, jednostkaLekcyjna.dzien);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, jednostkaLekcyjna);
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
        QJednostkaLekcyjna jednostkaLekcyjna = QJednostkaLekcyjna.jednostkaLekcyjna;
        JPQLQuery<JednostkaLekcyjna> query = from(jednostkaLekcyjna);
        Assert.notNull(typ, "typ is required");
        query.where(jednostkaLekcyjna.typ.eq(typ));
        Path<?>[] paths = new Path<?>[] { jednostkaLekcyjna.idSali, jednostkaLekcyjna.idProwadzacy, jednostkaLekcyjna.godzinaOd, jednostkaLekcyjna.godzinaDo, jednostkaLekcyjna.idPrzedmiotu, jednostkaLekcyjna.typ, jednostkaLekcyjna.dzien };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ID_SALI, jednostkaLekcyjna.idSali).map(ID_PROWADZACY, jednostkaLekcyjna.idProwadzacy).map(GODZINA_OD, jednostkaLekcyjna.godzinaOd).map(GODZINA_DO, jednostkaLekcyjna.godzinaDo).map(ID_PRZEDMIOTU, jednostkaLekcyjna.idPrzedmiotu).map(TYP, jednostkaLekcyjna.typ).map(DZIEN, jednostkaLekcyjna.dzien);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, jednostkaLekcyjna);
    }
}
