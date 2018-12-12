package pl.poznan.put.model;
import io.springlets.format.EntityFormat;
import org.springframework.roo.addon.javabean.annotations.RooEquals;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.JpaRelationType;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaRelation;
import org.springframework.util.Assert;

//import javax.jdo.annotations.Unique;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * = Budynek
 *
 * TODO Auto-generated class documentation
 *
 */
@RooJavaBean
@RooToString
@RooJpaEntity(entityFormatExpression = "#{nazwa}, #{adres}")
@RooEquals(isJpaEntity = true)
@Entity
@EntityFormat("#{nazwa}, #{adres}")
@Table(name = "budynek", indexes = {@Index(name = "budynek_idx",  columnList="id", unique = true)})
public class Budynek {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budynek_generator")
    @SequenceGenerator(name="budynek_generator", sequenceName = "budynek_seq", allocationSize = 1)
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
    @Column(name = "adres")
    @NotNull
    private String adres;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "budynek")
    @RooJpaRelation(type = JpaRelationType.AGGREGATION)
    private Set<Sala> sale = new HashSet<Sala>();

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
     * @return Budynek
     */
    public Budynek setId(Long id) {
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
     * @return Budynek
     */
    public Budynek setVersion(Integer version) {
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
     * @return Budynek
     */
    public Budynek setNazwa(String nazwa) {
        this.nazwa = nazwa;
        return this;
    }

    /**
     * Gets adres value
     *
     * @return String
     */
    public String getAdres() {
        return this.adres;
    }

    /**
     * Sets adres value
     *
     * @param adres
     * @return Budynek
     */
    public Budynek setAdres(String adres) {
        this.adres = adres;
        return this;
    }

    /**
     * Gets sale value
     *
     * @return Set
     */
    public Set<Sala> getSale() {
        return this.sale;
    }

    /**
     * Sets sale value
     *
     * @param sale
     * @return Budynek
     */
    public Budynek setSale(Set<Sala> sale) {
        this.sale = sale;
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
        if (!(obj instanceof Budynek)) {
            return false;
        }
        return getId() != null && Objects.equals(getId(), ((Budynek) obj).getId());
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
        return "Budynek {" + "id='" + id + '\'' + ", version='" + version + '\'' + ", nazwa='" + nazwa + '\'' + ", adres='" + adres + '\'' + "}" + super.toString();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param saleToAdd
     */
    public void addToSale(Iterable<Sala> saleToAdd) {
        Assert.notNull(saleToAdd, ITERABLE_TO_ADD_CANT_BE_NULL_MESSAGE);
        for (Sala item : saleToAdd) {
            this.sale.add(item);
            item.setBudynek(this);
        }
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param saleToRemove
     */
    public void removeFromSale(Iterable<Sala> saleToRemove) {
        Assert.notNull(saleToRemove, ITERABLE_TO_REMOVE_CANT_BE_NULL_MESSAGE);
        for (Sala item : saleToRemove) {
            this.sale.remove(item);
            item.setBudynek(null);
        }
    }
}
