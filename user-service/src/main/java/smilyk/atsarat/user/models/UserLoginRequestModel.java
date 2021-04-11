package smilyk.atsarat.user.models;

public class UserLoginRequestModel {
    private String password;
    private String mainEmail;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMainEmail() {
        return mainEmail;
    }

    public void setMainEmail(String mainEmail) {
        this.mainEmail = mainEmail;
    }

    public UserLoginRequestModel() {
    }

    public UserLoginRequestModel(String password, String mainEmail) {
        this.password = password;
        this.mainEmail = mainEmail;
    }
}
