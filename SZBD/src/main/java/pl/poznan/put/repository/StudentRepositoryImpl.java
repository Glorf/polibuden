package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import pl.poznan.put.model.Student;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import pl.poznan.put.model.Grupa;
import pl.poznan.put.model.QStudent;

/**
 * = StudentRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = StudentRepositoryCustom.class)
@Transactional
public class StudentRepositoryImpl extends QueryDslRepositorySupportExt<Student> implements StudentRepositoryCustom {

    /**
     * Default constructor
     */
    StudentRepositoryImpl() {
        super(Student.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String IMIE = "imie";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String PODGRUPA = "podgrupa";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String NAZWISKO = "nazwisko";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Student> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QStudent student = QStudent.student;
        JPQLQuery<Student> query = from(student);
        Path<?>[] paths = new Path<?>[] { student.podgrupa, student.imie, student.nazwisko };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(PODGRUPA, student.podgrupa).map(IMIE, student.imie).map(NAZWISKO, student.nazwisko);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, student);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Student> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QStudent student = QStudent.student;
        JPQLQuery<Student> query = from(student);
        Path<?>[] paths = new Path<?>[] { student.podgrupa, student.imie, student.nazwisko };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(student.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(PODGRUPA, student.podgrupa).map(IMIE, student.imie).map(NAZWISKO, student.nazwisko);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, student);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param podgrupa
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Student> findByPodgrupa(Grupa podgrupa, GlobalSearch globalSearch, Pageable pageable) {
        QStudent student = QStudent.student;
        JPQLQuery<Student> query = from(student);
        Assert.notNull(podgrupa, "podgrupa is required");
        query.where(student.podgrupa.eq(podgrupa));
        Path<?>[] paths = new Path<?>[] { student.podgrupa, student.imie, student.nazwisko };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(PODGRUPA, student.podgrupa).map(IMIE, student.imie).map(NAZWISKO, student.nazwisko);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, student);
    }
}
