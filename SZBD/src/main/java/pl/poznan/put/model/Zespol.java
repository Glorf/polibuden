package pl.poznan.put.model;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

import javax.persistence.*;

import io.springlets.format.EntityFormat;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import org.springframework.roo.addon.jpa.annotations.entity.JpaRelationType;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaRelation;
import java.util.Objects;

import org.springframework.util.Assert;

/**
 * = Zespol
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity(entityFormatExpression = "#{nazwa}")
@RooEquals(isJpaEntity = true)
@Entity
@Table(name = "zespol", uniqueConstraints={
        @UniqueConstraint(columnNames = {"wydzial", "nazwa"}),},
        indexes = {@Index(name = "zespol_idx",  columnList="id", unique = true)})
@EntityFormat("#{nazwa}")
public class Zespol {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zespol_generator")
    @SequenceGenerator(name="zespol_generator", sequenceName = "zespol_seq", allocationSize = 1)
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
    @JoinColumn(name = "wydzial")
    @EntityFormat
    private Wydzial wydzial;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "nazwa")
    @NotNull
    private String nazwa;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "zespol")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<Wykladowca> wykladowcy = new HashSet<Wykladowca>();

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
     * @return Zespol
     */
    public Zespol setId(Long id) {
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
     * @return Zespol
     */
    public Zespol setVersion(Integer version) {
        this.version = version;
        return this;
    }

    /**
     * Gets wydzial value
     *
     * @return Wydzial
     */
    public Wydzial getWydzial() {
        return this.wydzial;
    }

    /**
     * Sets wydzial value
     *
     * @param wydzial
     * @return Zespol
     */
    public Zespol setWydzial(Wydzial wydzial) {
        this.wydzial = wydzial;
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
     * @return Zespol
     */
    public Zespol setNazwa(String nazwa) {
        this.nazwa = nazwa;
        return this;
    }

    /**
     * Gets wykladowcy value
     *
     * @return Set
     */
    public Set<Wykladowca> getWykladowcy() {
        return this.wykladowcy;
    }

    /**
     * Sets wykladowcy value
     *
     * @param wykladowcy
     * @return Zespol
     */
    public Zespol setWykladowcy(Set<Wykladowca> wykladowcy) {
        this.wykladowcy = wykladowcy;
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
        if (!(obj instanceof Zespol)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Zespol) obj).getId());
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
        return "Zespol {" + "id='" + id + '\'' + ", version='" + version + '\'' + ", nazwa='" + nazwa + '\'' + "}" + super.toString();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowcyToAdd
     */
    public void addToWykladowcy(Iterable<Wykladowca> wykladowcyToAdd) {
        Assert.notNull(wykladowcyToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (Wykladowca item : wykladowcyToAdd) {
            this.wykladowcy.add(item);
            item.setZespol(this);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowcyToRemove
     */
    public void removeFromWykladowcy(Iterable<Wykladowca> wykladowcyToRemove) {
        Assert.notNull(wykladowcyToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (Wykladowca item : wykladowcyToRemove) {
            this.wykladowcy.remove(item);
            item.setZespol(null);
        }
    }
}
