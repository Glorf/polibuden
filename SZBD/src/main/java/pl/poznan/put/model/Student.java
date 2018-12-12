package pl.poznan.put.model;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

import javax.persistence.*;

import io.springlets.format.EntityFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * = Student
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity(entityFormatExpression = "#{imie} #{nazwisko} (#{id})")
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat("#{imie} #{nazwisko} (#{id})")
@Table(name = "student", indexes = {@Index(name = "student_idx",  columnList="id", unique = true),
        @Index(name = "student_name_idx", columnList="nazwisko", unique = false)})
public class Student {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_generator")
    @SequenceGenerator(name="student_generator", sequenceName = "student_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "version", nullable = false, columnDefinition = "integer default 1")
    @Version
    private Integer version;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "podgrupa")
    @EntityFormat
    private Grupa podgrupa;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "imie")
    @NotNull
    @Pattern(regexp = "[^0-9!@#$%^&*()=+<>?]*", message = "Imię nie może zawierać cyfr i znaków specjalnych")
    private String imie;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "nazwisko")
    @NotNull
    @Pattern(regexp = "[^0-9!@#$%^&*()=+<>?]*", message = "Nazwisko nie może zawierać cyfr i znaków specjalnych")
    private String nazwisko;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE = "The given Iterable of items to add can't be null!";

    /**
     * Gets id value
     *
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets id value
     *
     * @param id
     * @return Student
     */
    public Student setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets version value
     *
     * @return Integer
     */
    public Integer getVersion() {
        return this.version;
    }

    /**
     * Sets version value
     *
     * @param version
     * @return Student
     */
    public Student setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * Gets podgrupa value
     *
     * @return Grupa
     */
    public Grupa getPodgrupa() {
        return this.podgrupa;
    }

    /**
     * Sets podgrupa value
     *
     * @param podgrupa
     * @return Student
     */
    public Student setPodgrupa(Grupa podgrupa) {
        this.podgrupa = podgrupa;
        return this;
    }

    /**
     * Gets imie value
     *
     * @return String
     */
    public String getImie() {
        return this.imie;
    }

    /**
     * Sets imie value
     *
     * @param imie
     * @return Student
     */
    public Student setImie(String imie) {
        this.imie = imie;
        return this;
    }

    /**
     * Gets nazwisko value
     *
     * @return String
     */
    public String getNazwisko() {
        return this.nazwisko;
    }

    /**
     * Sets nazwisko value
     *
     * @param nazwisko
     * @return Student
     */
    public Student setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
        return this;
    }

    /**
     * This `equals` implementation is specific for JPA entities and uses
     * the entity identifier for it, following the article in
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @param obj
     * @return Boolean
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        // instanceof is false if the instance is null
        if (!(obj instanceof Student)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Student) obj).getId());
    }

    /**
     * This `hashCode` implementation is specific for JPA entities and uses a fixed `int` value to be able
     * to identify the entity in collections after a new id is assigned to the entity, following the article in
     * https://vladmihalcea.com/2016/06/06/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
     *
     * @return Integer
     */
    public int hashCode() {
        return 31;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String toString() {
        return "Student {" + "id='" + id + '\'' + ", version='" + version + '\'' + ", imie='" + imie + '\'' + ", nazwisko='" + nazwisko + '\'' + "}" + super.toString();
    }
}
