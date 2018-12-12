package pl.poznan.put.service.impl;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import pl.poznan.put.service.api.GrupaService;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.validation.MessageI18n;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poznan.put.model.Grupa;
import pl.poznan.put.model.JednostkaLekcyjna;
import pl.poznan.put.model.Kierunek;
import pl.poznan.put.model.Student;
import pl.poznan.put.repository.GrupaRepository;
import pl.poznan.put.service.api.JednostkaLekcyjnaService;
import pl.poznan.put.service.api.StudentService;

/**
 * = GrupaServiceImpl
 *
 * TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = GrupaService.class)
@Service
@Transactional
public class GrupaServiceImpl implements GrupaService {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private GrupaRepository grupaRepository;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private StudentService studentService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private JednostkaLekcyjnaService jednostkaLekcyjnaService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param grupaRepository
     * @param jednostkaLekcyjnaService
     * @param studentService
     */
    @Autowired
    public GrupaServiceImpl(GrupaRepository grupaRepository, @Lazy JednostkaLekcyjnaService jednostkaLekcyjnaService, @Lazy StudentService studentService) {
        setGrupaRepository(grupaRepository);
        setJednostkaLekcyjnaService(jednostkaLekcyjnaService);
        setStudentService(studentService);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return GrupaRepository
     */
    public GrupaRepository getGrupaRepository() {
        return grupaRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupaRepository
     */
    public void setGrupaRepository(GrupaRepository grupaRepository) {
        this.grupaRepository = grupaRepository;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return JednostkaLekcyjnaService
     */
    public JednostkaLekcyjnaService getJednostkaLekcyjnaService() {
        return jednostkaLekcyjnaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkaLekcyjnaService
     */
    public void setJednostkaLekcyjnaService(JednostkaLekcyjnaService jednostkaLekcyjnaService) {
        this.jednostkaLekcyjnaService = jednostkaLekcyjnaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return StudentService
     */
    public StudentService getStudentService() {
        return studentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param studentService
     */
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @return Map
     */
    public Map<String, List<MessageI18n>> validate(Grupa grupa) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param jednostkiLekcyjneToAdd
     * @return Grupa
     */
    @Transactional
    public Grupa addToJednostkiLekcyjne(Grupa grupa, Iterable<Long> jednostkiLekcyjneToAdd) {
        List<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjneToAdd);
        grupa.addToJednostkiLekcyjne(jednostkiLekcyjne);
        return getGrupaRepository().save(grupa);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param studenciToAdd
     * @return Grupa
     */
    @Transactional
    public Grupa addToStudenci(Grupa grupa, Iterable<Long> studenciToAdd) {
        List<Student> studenci = getStudentService().findAll(studenciToAdd);
        grupa.addToStudenci(studenci);
        return getGrupaRepository().save(grupa);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param jednostkiLekcyjneToRemove
     * @return Grupa
     */
    @Transactional
    public Grupa removeFromJednostkiLekcyjne(Grupa grupa, Iterable<Long> jednostkiLekcyjneToRemove) {
        List<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjneToRemove);
        grupa.removeFromJednostkiLekcyjne(jednostkiLekcyjne);
        return getGrupaRepository().save(grupa);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param studenciToRemove
     * @return Grupa
     */
    @Transactional
    public Grupa removeFromStudenci(Grupa grupa, Iterable<Long> studenciToRemove) {
        List<Student> studenci = getStudentService().findAll(studenciToRemove);
        grupa.removeFromStudenci(studenci);
        return getGrupaRepository().save(grupa);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param jednostkiLekcyjne
     * @return Grupa
     */
    @Transactional
    public Grupa setJednostkiLekcyjne(Grupa grupa, Iterable<Long> jednostkiLekcyjne) {
        List<JednostkaLekcyjna> items = getJednostkaLekcyjnaService().findAll(jednostkiLekcyjne);
        Set<JednostkaLekcyjna> currents = grupa.getJednostkiLekcyjne();
        Set<JednostkaLekcyjna> toRemove = new HashSet<JednostkaLekcyjna>();
        for (Iterator<JednostkaLekcyjna> iterator = currents.iterator(); iterator.hasNext(); ) {
            JednostkaLekcyjna nextJednostkaLekcyjna = iterator.next();
            if (items.contains(nextJednostkaLekcyjna)) {
                items.remove(nextJednostkaLekcyjna);
            } else {
                toRemove.add(nextJednostkaLekcyjna);
            }
        }
        grupa.removeFromJednostkiLekcyjne(toRemove);
        grupa.addToJednostkiLekcyjne(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        grupa.setVersion(grupa.getVersion() + 1);
        return getGrupaRepository().save(grupa);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param studenci
     * @return Grupa
     */
    @Transactional
    public Grupa setStudenci(Grupa grupa, Iterable<Long> studenci) {
        List<Student> items = getStudentService().findAll(studenci);
        Set<Student> currents = grupa.getStudenci();
        Set<Student> toRemove = new HashSet<Student>();
        for (Iterator<Student> iterator = currents.iterator(); iterator.hasNext(); ) {
            Student nextStudent = iterator.next();
            if (items.contains(nextStudent)) {
                items.remove(nextStudent);
            } else {
                toRemove.add(nextStudent);
            }
        }
        grupa.removeFromStudenci(toRemove);
        grupa.addToStudenci(items);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        grupa.setVersion(grupa.getVersion() + 1);
        return getGrupaRepository().save(grupa);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     */
    @Transactional
    public void delete(Grupa grupa) {
        // Clear bidirectional many-to-one child relationship with Kierunek
        if (grupa.getKierunek() != null) {
            grupa.getKierunek().getGrupy().remove(grupa);
        }
        // Clear bidirectional many-to-many parent relationship with JednostkaLekcyjna
        for (JednostkaLekcyjna item : grupa.getJednostkiLekcyjne()) {
            item.getGrupas().remove(grupa);
        }
        // Clear bidirectional one-to-many parent relationship with Student
        for (Student item : grupa.getStudenci()) {
            item.setPodgrupa(null);
        }
        getGrupaRepository().delete(grupa);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entities
     * @return List
     */
    @Transactional
    public List<Grupa> save(Iterable<Grupa> entities) {
        return getGrupaRepository().save(entities);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     */
    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Grupa> toDelete = getGrupaRepository().findAll(ids);
        getGrupaRepository().deleteInBatch(toDelete);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @return Grupa
     */
    @Transactional
    public Grupa save(Grupa entity) {
        return getGrupaRepository().save(entity);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Grupa
     */
    public Grupa findOne(Long id) {
        return getGrupaRepository().findOne(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Grupa
     */
    public Grupa findOneForUpdate(Long id) {
        return getGrupaRepository().findOneDetached(id);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return List
     */
    public List<Grupa> findAll(Iterable<Long> ids) {
        return getGrupaRepository().findAll(ids);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return List
     */
    public List<Grupa> findAll() {
        return getGrupaRepository().findAll();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Long
     */
    public long count() {
        return getGrupaRepository().count();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Grupa> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getGrupaRepository().findAll(globalSearch, pageable);
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
        return getGrupaRepository().findAllByIdsIn(ids, globalSearch, pageable);
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
        return getGrupaRepository().findByKierunek(kierunek, globalSearch, pageable);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @return Long
     */
    public long countByKierunek(Kierunek kierunek) {
        return getGrupaRepository().countByKierunek(kierunek);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<Grupa> getEntityType() {
        return Grupa.class;
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
