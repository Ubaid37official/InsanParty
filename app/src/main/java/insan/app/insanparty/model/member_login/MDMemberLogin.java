package insan.app.insanparty.model.member_login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MDMemberLogin {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("user")
    @Expose
    private UserMember user;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public UserMember getUser() {
        return user;
    }

    public void setUser(UserMember user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
