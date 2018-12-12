package pl.poznan.put.repository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustom;
import pl.poznan.put.model.Student;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.poznan.put.model.Grupa;

/**
 * = StudentRepositoryCustom
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustom(entity = Student.class)
public interface StudentRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param podgrupa
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Student> findByPodgrupa(Grupa podgrupa, GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Student> findAll(GlobalSearch globalSearch, Pageable pageable);

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Student> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
}
