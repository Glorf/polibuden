package pl.poznan.put.service.impl;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.StudentService;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.validation.MessageI18n;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.Grupa;
import pl.poznan.put.model.Student;
import pl.poznan.put.repository.StudentRepository;

/**
 * = StudentServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = StudentService.class)
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private StudentRepository studentRepository;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param studentRepository
     */
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        setStudentRepository(studentRepository);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return StudentRepository
     */
    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param studentRepository
     */
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param student
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Student student) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param student
     */
    @Transactional
    public void delete(Student student) {
        // Clear bidirectional many-to-one child relationship with Grupa
        if (student.getPodgrupa() != null) {
            student.getPodgrupa().getStudenci().remove(student);
        }
        getStudentRepository().delete(student);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Student> save(Iterable<Student> entities) {
        return getStudentRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Student> toDelete = getStudentRepository().findAll(ids);
        getStudentRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Student
     */
    @Transactional
    public Student save(Student entity) {
        return getStudentRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Student
     */
    public Student findOne(Long id) {
        return getStudentRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Student
     */
    public Student findOneForUpdate(Long id) {
        return getStudentRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Student> findAll(Iterable<Long> ids) {
        return getStudentRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Student> findAll() {
        return getStudentRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getStudentRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Student> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getStudentRepository().findAll(globalSearch, pageable);
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
        return getStudentRepository().findAllByIdsIn(ids, globalSearch, pageable);
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
        return getStudentRepository().findByPodgrupa(podgrupa, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param podgrupa
     * @return Long
     */
    public long countByPodgrupa(Grupa podgrupa) {
        return getStudentRepository().countByPodgrupa(podgrupa);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Student> getEntityType() {
        return Student.class;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Long> getIdType() {
        return Long.class;
    }
}
