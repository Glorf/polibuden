package pl.poznan.put.model;
import io.springlets.format.EntityFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.JpaRelationType;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaRelation;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * = Wykladowca
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity(entityFormatExpression = "#{imie} #{nazwisko}")
@RooEquals(isJpaEntity = true)
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "funkcjaStaz",
                procedureName = "staz",
                parameters = {
                        @StoredProcedureParameter(name = "id_prac", mode = ParameterMode.IN, type = Long.class)
                }
        )
})
@Entity
@EntityFormat("#{imie} #{nazwisko}")
@Table(name = "wykladowca", indexes = {@Index(name = "wykladowca_idx",  columnList="id", unique = true),
        @Index(name = "wykladowca_name_idx", columnList="nazwisko", unique = false)})

public class Wykladowca {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wykladowca_generator")
    @SequenceGenerator(name="wykladowca_generator", sequenceName = "wykladowca_seq", allocationSize = 1)
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zespol")
    @EntityFormat
    private Zespol zespol;

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
    @Column(name = "pensja")
    @NotNull
    @NumberFormat
    private Integer pensja;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "zatrudniony")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date zatrudniony;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "idProwadzacy")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<JednostkaLekcyjna> jednostkiLekcyjne = new HashSet<JednostkaLekcyjna>();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stopien")
    @EntityFormat
    private Stopien stopien;

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
     * @return Wykladowca
     */
    public Wykladowca setId(Long id) {
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
     * @return Wykladowca
     */
    public Wykladowca setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * Gets zespol value
     *
     * @return Zespol
     */
    public Zespol getZespol() {
        return this.zespol;
    }

    /**
     * Sets zespol value
     *
     * @param zespol
     * @return Wykladowca
     */
    public Wykladowca setZespol(Zespol zespol) {
        this.zespol = zespol;
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
     * @return Wykladowca
     */
    public Wykladowca setImie(String imie) {
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
     * @return Wykladowca
     */
    public Wykladowca setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
        return this;
    }

    /**
     * Gets pensja value
     *
     * @return Integer
     */
    public Integer getPensja() {
        return this.pensja;
    }

    /**
     * Sets pensja value
     *
     * @param pensja
     * @return Wykladowca
     */
    public Wykladowca setPensja(Integer pensja) {
        this.pensja = pensja;
        return this;
    }

    /**
     * Gets zatrudniony value
     *
     * @return Date
     */
    public Date getZatrudniony() {
        return this.zatrudniony;
    }

    /**
     * Sets zatrudniony value
     *
     * @param zatrudniony
     * @return Wykladowca
     */
    public Wykladowca setZatrudniony(Date zatrudniony) {
        this.zatrudniony = zatrudniony;
        return this;
    }

    /**
     * Gets jednostkiLekcyjne value
     *
     * @return Set
     */
    public Set<JednostkaLekcyjna> getJednostkiLekcyjne() {
        return this.jednostkiLekcyjne;
    }

    /**
     * Sets jednostkiLekcyjne value
     *
     * @param jednostkiLekcyjne
     * @return Wykladowca
     */
    public Wykladowca setJednostkiLekcyjne(Set<JednostkaLekcyjna> jednostkiLekcyjne) {
        this.jednostkiLekcyjne = jednostkiLekcyjne;
        return this;
    }

    /**
     * Gets stopien value
     *
     * @return Stopien
     */
    public Stopien getStopien() {
        return this.stopien;
    }

    /**
     * Sets stopien value
     *
     * @param stopien
     * @return Wykladowca
     */
    public Wykladowca setStopien(Stopien stopien) {
        this.stopien = stopien;
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
        if (!(obj instanceof Wykladowca)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Wykladowca) obj).getId());
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
        return "Wykladowca {" + "id='" + id + '\'' + ", version='" + version + '\'' + ", imie='" + imie + '\'' + ", nazwisko='" + nazwisko + '\'' + ", pensja='" + pensja + '\'' + ", zatrudniony='" + zatrudniony == null ? null : java.text.DateFormat.getDateTimeInstance().format(zatrudniony) + '\'' + "}" + super.toString();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkiLekcyjneToAdd
     */
    public void addToJednostkiLekcyjne(Iterable<JednostkaLekcyjna> jednostkiLekcyjneToAdd) {
        Assert.notNull(jednostkiLekcyjneToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (JednostkaLekcyjna item : jednostkiLekcyjneToAdd) {
            this.jednostkiLekcyjne.add(item);
            item.setIdProwadzacy(this);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkiLekcyjneToRemove
     */
    public void removeFromJednostkiLekcyjne(Iterable<JednostkaLekcyjna> jednostkiLekcyjneToRemove) {
        Assert.notNull(jednostkiLekcyjneToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (JednostkaLekcyjna item : jednostkiLekcyjneToRemove) {
            this.jednostkiLekcyjne.remove(item);
            item.setIdProwadzacy(null);
        }
    }
}
