package smilyk.atsarat.sceduler.models;


import smilyk.atsarat.sceduler.enums.Services;

import javax.persistence.*;
import java.io.Serializable;

import java.time.LocalDateTime;

@Entity
@Table(name = "plan")
public class PlanEntity implements Serializable{
    private static final long serialVersionUID = 2741378878902993758L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String uuidPlan;

    @Column(nullable = false)
    private String uuidChild;

    private Boolean deleted = false;
    private Boolean sendEmail = true;

    @Enumerated(EnumType.STRING)
    private Services service;

    private LocalDateTime date;
    private Boolean monday;
    //    private Double monday_time;
    private Boolean tuesday;
    //    private Double tuesday_time;
    private Boolean wednesday;
    //    private Double wednesday_time;
    private Boolean thursday;
    //    private Double thursday_time;
    private Boolean friday;
    //    private Double friday_time;
    private Boolean saturday;
    //    private Double saturday_time;
    private Boolean sunday;
//    private Double sunday_time;

    public PlanEntity() {
    }

    public PlanEntity(long id, String uuidPlan, String uuidChild, Boolean deleted,
                      Boolean sendEmail, Services service, LocalDateTime date,
                      Boolean monday, Boolean tuesday, Boolean wednesday,
                      Boolean thursday, Boolean friday, Boolean saturday, Boolean sunday) {
        this.id = id;
        this.uuidPlan = uuidPlan;
        this.uuidChild = uuidChild;
        this.deleted = deleted;
        this.sendEmail = sendEmail;
        this.service = service;
        this.date = date;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuidPlan() {
        return uuidPlan;
    }

    public void setUuidPlan(String uuidPlan) {
        this.uuidPlan = uuidPlan;
    }

    public String getUuidChild() {
        return uuidChild;
    }

    public void setUuidChild(String uuidChild) {
        this.uuidChild = uuidChild;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getMonday() {
        return monday;
    }

    public void setMonday(Boolean monday) {
        this.monday = monday;
    }

    public Boolean getTuesday() {
        return tuesday;
    }

    public void setTuesday(Boolean tuesday) {
        this.tuesday = tuesday;
    }

    public Boolean getWednesday() {
        return wednesday;
    }

    public void setWednesday(Boolean wednesday) {
        this.wednesday = wednesday;
    }

    public Boolean getThursday() {
        return thursday;
    }

    public void setThursday(Boolean thursday) {
        this.thursday = thursday;
    }

    public Boolean getFriday() {
        return friday;
    }

    public void setFriday(Boolean friday) {
        this.friday = friday;
    }

    public Boolean getSaturday() {
        return saturday;
    }

    public void setSaturday(Boolean saturday) {
        this.saturday = saturday;
    }

    public Boolean getSunday() {
        return sunday;
    }

    public void setSunday(Boolean sunday) {
        this.sunday = sunday;
    }
}
