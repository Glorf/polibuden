package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.Przedmiot;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.QPrzedmiot;

/**
 * = PrzedmiotRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = PrzedmiotRepositoryCustom.class)
@Transactional
public class PrzedmiotRepositoryImpl extends QueryDslRepositorySupportExt<Przedmiot> implements PrzedmiotRepositoryCustom {

    /**
     * Default constructor
     */
    PrzedmiotRepositoryImpl() {
        super(Przedmiot.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String NAZWA = "nazwa";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ECTS = "ects";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Przedmiot> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QPrzedmiot przedmiot = QPrzedmiot.przedmiot;
        JPQLQuery<Przedmiot> query = from(przedmiot);
        Path<?>[] paths = new Path<?>[] { przedmiot.nazwa, przedmiot.ects };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA, przedmiot.nazwa).map(ECTS, przedmiot.ects);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, przedmiot);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Przedmiot> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QPrzedmiot przedmiot = QPrzedmiot.przedmiot;
        JPQLQuery<Przedmiot> query = from(przedmiot);
        Path<?>[] paths = new Path<?>[] { przedmiot.nazwa, przedmiot.ects };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(przedmiot.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA, przedmiot.nazwa).map(ECTS, przedmiot.ects);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, przedmiot);
    }
}
