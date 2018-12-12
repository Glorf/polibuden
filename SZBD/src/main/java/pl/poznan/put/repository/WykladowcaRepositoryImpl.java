package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.Wykladowca;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pl.poznan.put.model.QWykladowca;
import pl.poznan.put.model.Stopien;
import pl.poznan.put.model.Zespol;

/**
 * = WykladowcaRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = WykladowcaRepositoryCustom.class)
@Transactional
public class WykladowcaRepositoryImpl extends QueryDslRepositorySupportExt<Wykladowca> implements WykladowcaRepositoryCustom {

    /**
     * Default constructor
     */
    WykladowcaRepositoryImpl() {
        super(Wykladowca.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ZATRUDNIONY = "zatrudniony";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ZESPOL = "zespol";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String IMIE = "imie";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String PENSJA = "pensja";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String STOPIEN = "stopien";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String NAZWISKO = "nazwisko";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Wykladowca> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QWykladowca wykladowca = QWykladowca.wykladowca;
        JPQLQuery<Wykladowca> query = from(wykladowca);
        Path<?>[] paths = new Path<?>[] { wykladowca.zespol, wykladowca.imie, wykladowca.nazwisko, wykladowca.pensja, wykladowca.zatrudniony, wykladowca.stopien };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ZESPOL, wykladowca.zespol).map(IMIE, wykladowca.imie).map(NAZWISKO, wykladowca.nazwisko).map(PENSJA, wykladowca.pensja).map(ZATRUDNIONY, wykladowca.zatrudniony).map(STOPIEN, wykladowca.stopien);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, wykladowca);
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
        QWykladowca wykladowca = QWykladowca.wykladowca;
        JPQLQuery<Wykladowca> query = from(wykladowca);
        Path<?>[] paths = new Path<?>[] { wykladowca.zespol, wykladowca.imie, wykladowca.nazwisko, wykladowca.pensja, wykladowca.zatrudniony, wykladowca.stopien };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(wykladowca.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(ZESPOL, wykladowca.zespol).map(IMIE, wykladowca.imie).map(NAZWISKO, wykladowca.nazwisko).map(PENSJA, wykladowca.pensja).map(ZATRUDNIONY, wykladowca.zatrudniony).map(STOPIEN, wykladowca.stopien);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, wykladowca);
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
        QWykladowca wykladowca = QWykladowca.wykladowca;
        JPQLQuery<Wykladowca> query = from(wykladowca);
        Assert.notNull(stopien, "stopien is required");
        query.where(wykladowca.stopien.eq(stopien));
        Path<?>[] paths = new Path<?>[] { wykladowca.zespol, wykladowca.imie, wykladowca.nazwisko, wykladowca.pensja, wykladowca.zatrudniony, wykladowca.stopien };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ZESPOL, wykladowca.zespol).map(IMIE, wykladowca.imie).map(NAZWISKO, wykladowca.nazwisko).map(PENSJA, wykladowca.pensja).map(ZATRUDNIONY, wykladowca.zatrudniony).map(STOPIEN, wykladowca.stopien);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, wykladowca);
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
        QWykladowca wykladowca = QWykladowca.wykladowca;
        JPQLQuery<Wykladowca> query = from(wykladowca);
        Assert.notNull(zespol, "zespol is required");
        query.where(wykladowca.zespol.eq(zespol));
        Path<?>[] paths = new Path<?>[] { wykladowca.zespol, wykladowca.imie, wykladowca.nazwisko, wykladowca.pensja, wykladowca.zatrudniony, wykladowca.stopien };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ZESPOL, wykladowca.zespol).map(IMIE, wykladowca.imie).map(NAZWISKO, wykladowca.nazwisko).map(PENSJA, wykladowca.pensja).map(ZATRUDNIONY, wykladowca.zatrudniony).map(STOPIEN, wykladowca.stopien);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, wykladowca);
    }
}
