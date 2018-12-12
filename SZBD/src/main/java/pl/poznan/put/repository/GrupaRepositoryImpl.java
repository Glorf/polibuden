package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.Grupa;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pl.poznan.put.model.Kierunek;
import pl.poznan.put.model.QGrupa;

/**
 * = GrupaRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = GrupaRepositoryCustom.class)
@Transactional
public class GrupaRepositoryImpl extends QueryDslRepositorySupportExt<Grupa> implements GrupaRepositoryCustom {

    /**
     * Default constructor
     */
    GrupaRepositoryImpl() {
        super(Grupa.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String KIERUNEK = "kierunek";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String NAZWA_GRUPY = "nazwaGrupy";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String NAZWA_PODGRUPY = "nazwaPodgrupy";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Grupa> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QGrupa grupa = QGrupa.grupa;
        JPQLQuery<Grupa> query = from(grupa);
        Path<?>[] paths = new Path<?>[] { grupa.nazwaGrupy, grupa.nazwaPodgrupy, grupa.kierunek };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA_GRUPY, grupa.nazwaGrupy).map(NAZWA_PODGRUPY, grupa.nazwaPodgrupy).map(KIERUNEK, grupa.kierunek);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, grupa);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Grupa> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QGrupa grupa = QGrupa.grupa;
        JPQLQuery<Grupa> query = from(grupa);
        Path<?>[] paths = new Path<?>[] { grupa.nazwaGrupy, grupa.nazwaPodgrupy, grupa.kierunek };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(grupa.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA_GRUPY, grupa.nazwaGrupy).map(NAZWA_PODGRUPY, grupa.nazwaPodgrupy).map(KIERUNEK, grupa.kierunek);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, grupa);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Grupa> findByKierunek(Kierunek kierunek, GlobalSearch globalSearch, Pageable pageable) {
        QGrupa grupa = QGrupa.grupa;
        JPQLQuery<Grupa> query = from(grupa);
        Assert.notNull(kierunek, "kierunek is required");
        query.where(grupa.kierunek.eq(kierunek));
        Path<?>[] paths = new Path<?>[] { grupa.nazwaGrupy, grupa.nazwaPodgrupy, grupa.kierunek };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(NAZWA_GRUPY, grupa.nazwaGrupy).map(NAZWA_PODGRUPY, grupa.nazwaPodgrupy).map(KIERUNEK, grupa.kierunek);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, grupa);
    }
}
