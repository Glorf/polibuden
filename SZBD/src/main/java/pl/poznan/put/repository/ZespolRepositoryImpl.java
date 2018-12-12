package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.Zespol;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pl.poznan.put.model.QZespol;
import pl.poznan.put.model.Wydzial;

/**
 * = ZespolRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = ZespolRepositoryCustom.class)
@Transactional
public class ZespolRepositoryImpl extends QueryDslRepositorySupportExt<Zespol> implements ZespolRepositoryCustom {

    /**
     * Default constructor
     */
    ZespolRepositoryImpl() {
        super(Zespol.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String WYDZIAL = "wydzial";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String NAZWA = "nazwa";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Zespol> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QZespol zespol = QZespol.zespol;
        JPQLQuery<Zespol> query = from(zespol);
        Path<?>[] paths = new Path<?>[] { zespol.wydzial, zespol.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(WYDZIAL, zespol.wydzial).map(NAZWA, zespol.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, zespol);
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
        QZespol zespol = QZespol.zespol;
        JPQLQuery<Zespol> query = from(zespol);
        Path<?>[] paths = new Path<?>[] { zespol.wydzial, zespol.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(zespol.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(WYDZIAL, zespol.wydzial).map(NAZWA, zespol.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, zespol);
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
        QZespol zespol = QZespol.zespol;
        JPQLQuery<Zespol> query = from(zespol);
        Assert.notNull(wydzial, "wydzial is required");
        query.where(zespol.wydzial.eq(wydzial));
        Path<?>[] paths = new Path<?>[] { zespol.wydzial, zespol.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(WYDZIAL, zespol.wydzial).map(NAZWA, zespol.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, zespol);
    }
}
