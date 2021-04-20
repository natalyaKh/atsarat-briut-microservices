package smilyk.atsarat.tsofim.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tsofimDetails")
public class TsofimDetails implements Serializable {

    private static final long serialVersionUID = 760616726060207402L;

    @Id
    @GeneratedValue
    private long id;

    private String uuidTsofimDetails;

    @Column(nullable = false)
    private String uuidChild;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String groupTs;

    @Column(nullable = false)
    private String school;

    @Column(nullable = false)
    private String childClass;

    private Boolean deleted = false;

    public TsofimDetails(long id, String uuidTsofimDetails, String uuidChild, String place, String groupTs, String school, String childClass, Boolean deleted) {
        this.id = id;
        this.uuidTsofimDetails = uuidTsofimDetails;
        this.uuidChild = uuidChild;
        this.place = place;
        this.groupTs = groupTs;
        this.school = school;
        this.childClass = childClass;
        this.deleted = deleted;
    }

    public TsofimDetails() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuidTsofimDetails() {
        return uuidTsofimDetails;
    }

    public void setUuidTsofimDetails(String uuidTsofimDetails) {
        this.uuidTsofimDetails = uuidTsofimDetails;
    }

    public String getUuidChild() {
        return uuidChild;
    }

    public void setUuidChild(String uuidChild) {
        this.uuidChild = uuidChild;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getGroupTs() {
        return groupTs;
    }

    public void setGroupTs(String groupTs) {
        this.groupTs = groupTs;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getChildClass() {
        return childClass;
    }

    public void setChildClass(String childClass) {
        this.childClass = childClass;
    }
}

