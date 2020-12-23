package rehanfoundation.app.insanparty.model.member_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MDMemberDetail {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("member")
    @Expose
    private MemberDetail member;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public MemberDetail getMember() {
        return member;
    }

    public void setMember(MemberDetail member) {
        this.member = member;
    }
}
