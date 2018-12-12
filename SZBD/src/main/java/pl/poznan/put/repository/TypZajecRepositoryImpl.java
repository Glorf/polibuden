package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.TypZajec;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.QTypZajec;

/**
 * = TypZajecRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = TypZajecRepositoryCustom.class)
@Transactional
public class TypZajecRepositoryImpl extends QueryDslRepositorySupportExt<TypZajec> implements TypZajecRepositoryCustom {

    /**
     * Default constructor
     */
    TypZajecRepositoryImpl() {
        super(TypZajec.class);
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
    public Page<TypZajec> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QTypZajec typZajec = QTypZajec.typZajec;
        JPQLQuery<TypZajec> query = from(typZajec);
        Path<?>[] paths = new Path<?>[] { typZajec.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA, typZajec.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, typZajec);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<TypZajec> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QTypZajec typZajec = QTypZajec.typZajec;
        JPQLQuery<TypZajec> query = from(typZajec);
        Path<?>[] paths = new Path<?>[] { typZajec.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(typZajec.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA, typZajec.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, typZajec);
    }
}
