package pl.poznan.put.repository;
import io.springlets.data.jpa.repository.DetachableJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import pl.poznan.put.model.Student;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.Grupa;

/**
 * = StudentRepository
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Student.class)
@Transactional
public interface StudentRepository extends DetachableJpaRepository<Student, Long>, StudentRepositoryCustom {

    /**
     * TODO Auto-generated method documentation
     *
     * @param podgrupa
     * @return Long
     */
    public abstract long countByPodgrupa(Grupa podgrupa);
}
