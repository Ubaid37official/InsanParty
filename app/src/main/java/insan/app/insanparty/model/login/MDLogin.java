package insan.app.insanparty.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MDLogin {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("user")
    @Expose
    private rehanfoundation.app.insanparty.model.login.User user;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public rehanfoundation.app.insanparty.model.login.User getUser() {
        return user;
    }

    public void setUser(rehanfoundation.app.insanparty.model.login.User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
