package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.Literatura;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pl.poznan.put.model.Przedmiot;
import pl.poznan.put.model.QLiteratura;

/**
 * = LiteraturaRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = LiteraturaRepositoryCustom.class)
@Transactional
public class LiteraturaRepositoryImpl extends QueryDslRepositorySupportExt<Literatura> implements LiteraturaRepositoryCustom {

    /**
     * Default constructor
     */
    LiteraturaRepositoryImpl() {
        super(Literatura.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ISBN = "isbn";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String AUTOR = "autor";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String WYDAWNICTWO = "wydawnictwo";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String TYTUL = "tytul";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Literatura> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QLiteratura literatura = QLiteratura.literatura;
        JPQLQuery<Literatura> query = from(literatura);
        Path<?>[] paths = new Path<?>[] { literatura.isbn, literatura.autor, literatura.tytul, literatura.wydawnictwo };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ISBN, literatura.isbn).map(AUTOR, literatura.autor).map(TYTUL, literatura.tytul).map(WYDAWNICTWO, literatura.wydawnictwo);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, literatura);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Literatura> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QLiteratura literatura = QLiteratura.literatura;
        JPQLQuery<Literatura> query = from(literatura);
        Path<?>[] paths = new Path<?>[] { literatura.isbn, literatura.autor, literatura.tytul, literatura.wydawnictwo };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(literatura.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(ISBN, literatura.isbn).map(AUTOR, literatura.autor).map(TYTUL, literatura.tytul).map(WYDAWNICTWO, literatura.wydawnictwo);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, literatura);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmioty
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Literatura> findByPrzedmiotyContains(Przedmiot przedmioty, GlobalSearch globalSearch, Pageable pageable) {
        QLiteratura literatura = QLiteratura.literatura;
        JPQLQuery<Literatura> query = from(literatura);
        Assert.notNull(przedmioty, "przedmioty is required");
        query.where(literatura.przedmioty.contains(przedmioty));
        Path<?>[] paths = new Path<?>[] { literatura.isbn, literatura.autor, literatura.tytul, literatura.wydawnictwo };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ISBN, literatura.isbn).map(AUTOR, literatura.autor).map(TYTUL, literatura.tytul).map(WYDAWNICTWO, literatura.wydawnictwo);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, literatura);
    }
}
