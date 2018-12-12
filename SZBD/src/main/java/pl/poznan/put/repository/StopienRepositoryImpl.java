package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.Stopien;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.QStopien;

/**
 * = StopienRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = StopienRepositoryCustom.class)
@Transactional
public class StopienRepositoryImpl extends QueryDslRepositorySupportExt<Stopien> implements StopienRepositoryCustom {

    /**
     * Default constructor
     */
    StopienRepositoryImpl() {
        super(Stopien.class);
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
    public Page<Stopien> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QStopien stopien = QStopien.stopien;
        JPQLQuery<Stopien> query = from(stopien);
        Path<?>[] paths = new Path<?>[] { stopien.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA, stopien.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, stopien);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Stopien> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QStopien stopien = QStopien.stopien;
        JPQLQuery<Stopien> query = from(stopien);
        Path<?>[] paths = new Path<?>[] { stopien.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(stopien.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA, stopien.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, stopien);
    }
}
