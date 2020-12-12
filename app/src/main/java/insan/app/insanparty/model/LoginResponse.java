package insan.app.insanparty.model;

import insan.app.insanparty.model.login.User;

public class LoginResponse {

    private boolean status;
    private String message;
    private User user;


    public LoginResponse(boolean status, String message, User user) {
        this.status = status;
        this.message = message;
        this.user = user;

    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }


}
