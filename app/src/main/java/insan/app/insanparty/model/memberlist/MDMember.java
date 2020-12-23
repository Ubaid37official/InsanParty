package rehanfoundation.app.insanparty.model.memberlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MDMember {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("members")
    @Expose
    private List<MemberList> members = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<MemberList> getMembers() {
        return members;
    }

    public void setMembers(List<MemberList> members) {
        this.members = members;
    }
}
