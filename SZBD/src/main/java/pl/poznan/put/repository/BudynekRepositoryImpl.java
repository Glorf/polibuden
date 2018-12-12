package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.Budynek;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.QBudynek;

/**
 * = BudynekRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = BudynekRepositoryCustom.class)
@Transactional
public class BudynekRepositoryImpl extends QueryDslRepositorySupportExt<Budynek> implements BudynekRepositoryCustom {

    /**
     * Default constructor
     */
    BudynekRepositoryImpl() {
        super(Budynek.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ADRES = "adres";

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
    public Page<Budynek> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QBudynek budynek = QBudynek.budynek;
        JPQLQuery<Budynek> query = from(budynek);
        Path<?>[] paths = new Path<?>[] { budynek.nazwa, budynek.adres };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA, budynek.nazwa).map(ADRES, budynek.adres);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, budynek);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Budynek> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QBudynek budynek = QBudynek.budynek;
        JPQLQuery<Budynek> query = from(budynek);
        Path<?>[] paths = new Path<?>[] { budynek.nazwa, budynek.adres };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(budynek.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA, budynek.nazwa).map(ADRES, budynek.adres);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, budynek);
    }
}
