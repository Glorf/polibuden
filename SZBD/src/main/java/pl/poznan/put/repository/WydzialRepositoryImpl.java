package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.Wydzial;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.QWydzial;

/**
 * = WydzialRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = WydzialRepositoryCustom.class)
@Transactional
public class WydzialRepositoryImpl extends QueryDslRepositorySupportExt<Wydzial> implements WydzialRepositoryCustom {

    /**
     * Default constructor
     */
    WydzialRepositoryImpl() {
        super(Wydzial.class);
    }

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
    public Page<Wydzial> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QWydzial wydzial = QWydzial.wydzial;
        JPQLQuery<Wydzial> query = from(wydzial);
        Path<?>[] paths = new Path<?>[] { wydzial.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA, wydzial.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, wydzial);
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
        QWydzial wydzial = QWydzial.wydzial;
        JPQLQuery<Wydzial> query = from(wydzial);
        Path<?>[] paths = new Path<?>[] { wydzial.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(wydzial.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA, wydzial.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, wydzial);
    }
}
