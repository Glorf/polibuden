package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.Sala;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pl.poznan.put.model.Budynek;
import pl.poznan.put.model.QSala;

/**
 * = SalaRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = SalaRepositoryCustom.class)
@Transactional
public class SalaRepositoryImpl extends QueryDslRepositorySupportExt<Sala> implements SalaRepositoryCustom {

    /**
     * Default constructor
     */
    SalaRepositoryImpl() {
        super(Sala.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String BUDYNEK = "budynek";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String LICZBA_MIEJSC = "liczbaMiejsc";

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
    public Page<Sala> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QSala sala = QSala.sala;
        JPQLQuery<Sala> query = from(sala);
        Path<?>[] paths = new Path<?>[] { sala.budynek, sala.nazwa, sala.liczbaMiejsc };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(BUDYNEK, sala.budynek).map(NAZWA, sala.nazwa).map(LICZBA_MIEJSC, sala.liczbaMiejsc);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, sala);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Sala> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QSala sala = QSala.sala;
        JPQLQuery<Sala> query = from(sala);
        Path<?>[] paths = new Path<?>[] { sala.budynek, sala.nazwa, sala.liczbaMiejsc };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(sala.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(BUDYNEK, sala.budynek).map(NAZWA, sala.nazwa).map(LICZBA_MIEJSC, sala.liczbaMiejsc);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, sala);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Sala> findByBudynek(Budynek budynek, GlobalSearch globalSearch, Pageable pageable) {
        QSala sala = QSala.sala;
        JPQLQuery<Sala> query = from(sala);
        Assert.notNull(budynek, "budynek is required");
        query.where(sala.budynek.eq(budynek));
        Path<?>[] paths = new Path<?>[] { sala.budynek, sala.nazwa, sala.liczbaMiejsc };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(BUDYNEK, sala.budynek).map(NAZWA, sala.nazwa).map(LICZBA_MIEJSC, sala.liczbaMiejsc);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, sala);
    }
}
