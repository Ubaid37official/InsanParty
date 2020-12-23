package rehanfoundation.app.insanparty.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MDProfile {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("user")
    @Expose
    private UserProfile user;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }
}
