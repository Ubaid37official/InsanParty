package insan.app.insanparty.model;

public class Member {

    private boolean status;
    private String message;

    public Member(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
