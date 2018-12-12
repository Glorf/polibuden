package pl.poznan.put.model;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import org.springframework.roo.addon.jpa.annotations.entity.JpaRelationType;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaRelation;
import io.springlets.format.EntityFormat;

import java.util.Objects;

import org.springframework.util.Assert;

/**
 * = Grupa
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity(entityFormatExpression = "#{nazwaGrupy} #{nazwaPodgrupy}")
@RooEquals(isJpaEntity = true)
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "funkcjaLiczbaOsob",
                procedureName = "get_osoby",
                parameters = {
                        @StoredProcedureParameter(name = "id_grupy", mode = ParameterMode.IN, type = Long.class)
                }
        )
})
@Entity
@Table(name = "grupa", uniqueConstraints={
        @UniqueConstraint(columnNames = {"nazwa_grupy", "nazwa_podgrupy"})})
@EntityFormat("#{nazwaGrupy} #{nazwaPodgrupy}")
public class Grupa {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grupa_generator")
    @SequenceGenerator(name="grupa_generator", sequenceName = "grupa_seq", allocationSize = 1)
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
    @Column(name = "nazwa_grupy")
    @NotNull
    private String nazwaGrupy;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "nazwa_podgrupy")
    @NotNull
    private String nazwaPodgrupy;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "podgrupa")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<Student> studenci = new HashSet<Student>();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "grupas")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<JednostkaLekcyjna> jednostkiLekcyjne = new HashSet<JednostkaLekcyjna>();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kierunek")
    @EntityFormat
    private Kierunek kierunek;

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
     * @return Grupa
     */
    public Grupa setId(Long id) {
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
     * @return Grupa
     */
    public Grupa setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * Gets nazwaGrupy value
     *
     * @return String
     */
    public String getNazwaGrupy() {
        return this.nazwaGrupy;
    }

    /**
     * Sets nazwaGrupy value
     *
     * @param nazwaGrupy
     * @return Grupa
     */
    public Grupa setNazwaGrupy(String nazwaGrupy) {
        this.nazwaGrupy = nazwaGrupy;
        return this;
    }

    /**
     * Gets nazwaPodgrupy value
     *
     * @return String
     */
    public String getNazwaPodgrupy() {
        return this.nazwaPodgrupy;
    }

    /**
     * Sets nazwaPodgrupy value
     *
     * @param nazwaPodgrupy
     * @return Grupa
     */
    public Grupa setNazwaPodgrupy(String nazwaPodgrupy) {
        this.nazwaPodgrupy = nazwaPodgrupy;
        return this;
    }

    /**
     * Gets studenci value
     *
     * @return Set
     */
    public Set<Student> getStudenci() {
        return this.studenci;
    }

    /**
     * Sets studenci value
     *
     * @param studenci
     * @return Grupa
     */
    public Grupa setStudenci(Set<Student> studenci) {
        this.studenci = studenci;
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
     * @return Grupa
     */
    public Grupa setJednostkiLekcyjne(Set<JednostkaLekcyjna> jednostkiLekcyjne) {
        this.jednostkiLekcyjne = jednostkiLekcyjne;
        return this;
    }

    /**
     * Gets kierunek value
     *
     * @return Kierunek
     */
    public Kierunek getKierunek() {
        return this.kierunek;
    }

    /**
     * Sets kierunek value
     *
     * @param kierunek
     * @return Grupa
     */
    public Grupa setKierunek(Kierunek kierunek) {
        this.kierunek = kierunek;
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
        if (!(obj instanceof Grupa)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Grupa) obj).getId());
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
        return "Grupa {" + "id='" + id + '\'' + ", version='" + version + '\'' + ", nazwaGrupy='" + nazwaGrupy + '\'' + ", nazwaPodgrupy='" + nazwaPodgrupy + '\'' + "}" + super.toString();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param studenciToAdd
     */
    public void addToStudenci(Iterable<Student> studenciToAdd) {
        Assert.notNull(studenciToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (Student item : studenciToAdd) {
            this.studenci.add(item);
            item.setPodgrupa(this);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param studenciToRemove
     */
    public void removeFromStudenci(Iterable<Student> studenciToRemove) {
        Assert.notNull(studenciToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (Student item : studenciToRemove) {
            this.studenci.remove(item);
            item.setPodgrupa(null);
        }
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
            item.getGrupas().add(this);
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
            item.getGrupas().remove(this);
        }
    }
}
