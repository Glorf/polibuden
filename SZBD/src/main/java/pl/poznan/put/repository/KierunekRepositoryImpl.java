package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.Kierunek;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pl.poznan.put.model.QKierunek;
import pl.poznan.put.model.Wydzial;

/**
 * = KierunekRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = KierunekRepositoryCustom.class)
@Transactional
public class KierunekRepositoryImpl extends QueryDslRepositorySupportExt<Kierunek> implements KierunekRepositoryCustom {

    /**
     * Default constructor
     */
    KierunekRepositoryImpl() {
        super(Kierunek.class);
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
    public Page<Kierunek> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QKierunek kierunek = QKierunek.kierunek;
        JPQLQuery<Kierunek> query = from(kierunek);
        Path<?>[] paths = new Path<?>[] { kierunek.wydzial, kierunek.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(WYDZIAL, kierunek.wydzial).map(NAZWA, kierunek.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, kierunek);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Kierunek> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QKierunek kierunek = QKierunek.kierunek;
        JPQLQuery<Kierunek> query = from(kierunek);
        Path<?>[] paths = new Path<?>[] { kierunek.wydzial, kierunek.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(kierunek.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(WYDZIAL, kierunek.wydzial).map(NAZWA, kierunek.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, kierunek);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Kierunek> findByWydzial(Wydzial wydzial, GlobalSearch globalSearch, Pageable pageable) {
        QKierunek kierunek = QKierunek.kierunek;
        JPQLQuery<Kierunek> query = from(kierunek);
        Assert.notNull(wydzial, "wydzial is required");
        query.where(kierunek.wydzial.eq(wydzial));
        Path<?>[] paths = new Path<?>[] { kierunek.wydzial, kierunek.nazwa };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(WYDZIAL, kierunek.wydzial).map(NAZWA, kierunek.nazwa);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, kierunek);
    }
}
