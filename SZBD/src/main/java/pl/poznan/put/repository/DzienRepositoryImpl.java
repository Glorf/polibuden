package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.Dzien;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.QDzien;

/**
 * = DzienRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = DzienRepositoryCustom.class)
@Transactional
public class DzienRepositoryImpl extends QueryDslRepositorySupportExt<Dzien> implements DzienRepositoryCustom {

    /**
     * Default constructor
     */
    DzienRepositoryImpl() {
        super(Dzien.class);
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
    public Page<Dzien> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QDzien dzien = QDzien.dzien;
        JPQLQuery<Dzien> query = from(dzien);
        Path<?>[] paths = new Path<?>[] { dzien.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA, dzien.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, dzien);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Dzien> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QDzien dzien = QDzien.dzien;
        JPQLQuery<Dzien> query = from(dzien);
        Path<?>[] paths = new Path<?>[] { dzien.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(dzien.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA, dzien.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, dzien);
    }
}
