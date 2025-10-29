package nhn.academy.model.login;

public class LoginMemberRequest {

    private String id;
    private String password;

    public LoginMemberRequest(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
