package smilyk.atsarat.children.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Children Entity
 */
@Entity
@Table(name = "children")
public class ChildrenEntity implements Serializable {
    private static final long serialVersionUID = -2081283438898844381L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String uuidChild;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String secondName;

    //teudat-zeut - needed for some atsarat briut. save in byCrypt
    @Column(nullable = false)
    private String tz;

    private String uuidParent;

    private String uuidRespPers;

    private Boolean deleted = false;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuidChild() {
        return uuidChild;
    }

    public void setUuidChild(String uuidChild) {
        this.uuidChild = uuidChild;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getUuidParent() {
        return uuidParent;
    }

    public void setUuidParent(String uuidParent) {
        this.uuidParent = uuidParent;
    }

    public String getUuidRespPers() {
        return uuidRespPers;
    }

    public void setUuidRespPers(String uuidRespPers) {
        this.uuidRespPers = uuidRespPers;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public ChildrenEntity() {

    }

    public ChildrenEntity(long id, String uuidChild, String firstName, String secondName, String tz, String uuidParent, String uuidRespPers, Boolean deleted) {
        this.id = id;
        this.uuidChild = uuidChild;
        this.firstName = firstName;
        this.secondName = secondName;
        this.tz = tz;
        this.uuidParent = uuidParent;
        this.uuidRespPers = uuidRespPers;
        this.deleted = deleted;
    }
}
