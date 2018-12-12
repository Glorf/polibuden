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
 * = Dzien
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity(entityFormatExpression = "#{nazwa}")
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat("#{nazwa}")
@Table(name = "dzien", indexes = {@Index(name = "dzien_idx",  columnList="id", unique = true)})
public class Dzien {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dzien_generator")
    @SequenceGenerator(name="dzien_generator", sequenceName = "dzien_seq", allocationSize = 1)
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
    @Column(name = "nazwa", unique = true)
    @NotNull
    private String nazwa;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "dzien")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<JednostkaLekcyjna> jednostkiLekcyjne = new HashSet<JednostkaLekcyjna>();

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
     * @return Dzien
     */
    public Dzien setId(Long id) {
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
     * @return Dzien
     */
    public Dzien setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * Gets nazwa value
     *
     * @return String
     */
    public String getNazwa() {
        return this.nazwa;
    }

    /**
     * Sets nazwa value
     *
     * @param nazwa
     * @return Dzien
     */
    public Dzien setNazwa(String nazwa) {
        this.nazwa = nazwa;
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
     * @return Dzien
     */
    public Dzien setJednostkiLekcyjne(Set<JednostkaLekcyjna> jednostkiLekcyjne) {
        this.jednostkiLekcyjne = jednostkiLekcyjne;
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
        if (!(obj instanceof Dzien)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Dzien) obj).getId());
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
        return "Dzien {" + "id='" + id + '\'' + ", version='" + version + '\'' + ", nazwa='" + nazwa + '\'' + "}" + super.toString();
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
            item.setDzien(this);
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
            item.setDzien(null);
        }
    }
}
