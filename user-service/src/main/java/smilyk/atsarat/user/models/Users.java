package smilyk.atsarat.user.models;




import javax.persistence.*;
import java.io.Serializable;


 /**
 * create instance of User
 */


@Entity
@Table(name = "user")
public class Users implements Serializable {

        private static final long serialVersionUID = -5937501502150399893L;

        @Id
        @GeneratedValue
        private long id;

        @Column(nullable = false)
        private String uuidUser;

        @Column(nullable = false)
        private String firstName;

        private String secondName;

        @Column(nullable = false)
        private String mainEmail;

        @Column(nullable = false)
        private String password;

        //teudat-zeut - needed for some atsarat briut. save in byCrypt
        private String tz;

        private String confirmEmailToken;

        private Boolean deleted = false;

        private Boolean confirmEmail = false;

    public Users() {
    }

    public Users(long id, String uuidUser, String firstName, String secondName, String mainEmail,
                 String password, String tz, String confirmEmailToken, Boolean deleted, Boolean confirmEmail) {
        this.id = id;
        this.uuidUser = uuidUser;
        this.firstName = firstName;
        this.secondName = secondName;
        this.mainEmail = mainEmail;
        this.password = password;
        this.tz = tz;
        this.confirmEmailToken = confirmEmailToken;
        this.deleted = deleted;
        this.confirmEmail = confirmEmail;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUuidUser(String uuidUser) {
        this.uuidUser = uuidUser;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setMainEmail(String mainEmail) {
        this.mainEmail = mainEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public void setConfirmEmailToken(String confirmEmailToken) {
        this.confirmEmailToken = confirmEmailToken;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void setConfirmEmail(Boolean confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public String getUuidUser() {
        return uuidUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getMainEmail() {
        return mainEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getTz() {
        return tz;
    }

    public String getConfirmEmailToken() {
        return confirmEmailToken;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Boolean getConfirmEmail() {
        return confirmEmail;
    }
}
