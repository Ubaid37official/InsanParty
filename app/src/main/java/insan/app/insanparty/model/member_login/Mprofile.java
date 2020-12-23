package rehanfoundation.app.insanparty.model.member_login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mprofile {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("father_name")
    @Expose
    private String fatherName;
    @SerializedName("cnic")
    @Expose
    private Integer cnic;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("social_links")
    @Expose
    private String socialLinks;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("qualification")
    @Expose
    private String qualification;
    @SerializedName("city_country")
    @Expose
    private String cityCountry;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("consistency")
    @Expose
    private String consistency;
    @SerializedName("political_background")
    @Expose
    private String politicalBackground;
    @SerializedName("passion")
    @Expose
    private String passion;
    @SerializedName("abilities")
    @Expose
    private String abilities;
    @SerializedName("political_interest")
    @Expose
    private String politicalInterest;
    @SerializedName("shadow_position")
    @Expose
    private String shadowPosition;
    @SerializedName("change_in_pakistan")
    @Expose
    private String changeInPakistan;
    @SerializedName("work_time")
    @Expose
    private String workTime;
    @SerializedName("perform_task")
    @Expose
    private String performTask;
    @SerializedName("abide_laws")
    @Expose
    private String abideLaws;
    @SerializedName("approved")
    @Expose
    private String approved;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Integer getCnic() {
        return cnic;
    }

    public void setCnic(Integer cnic) {
        this.cnic = cnic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(String socialLinks) {
        this.socialLinks = socialLinks;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getCityCountry() {
        return cityCountry;
    }

    public void setCityCountry(String cityCountry) {
        this.cityCountry = cityCountry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsistency() {
        return consistency;
    }

    public void setConsistency(String consistency) {
        this.consistency = consistency;
    }

    public String getPoliticalBackground() {
        return politicalBackground;
    }

    public void setPoliticalBackground(String politicalBackground) {
        this.politicalBackground = politicalBackground;
    }

    public String getPassion() {
        return passion;
    }

    public void setPassion(String passion) {
        this.passion = passion;
    }

    public String getAbilities() {
        return abilities;
    }

    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }

    public String getPoliticalInterest() {
        return politicalInterest;
    }

    public void setPoliticalInterest(String politicalInterest) {
        this.politicalInterest = politicalInterest;
    }

    public String getShadowPosition() {
        return shadowPosition;
    }

    public void setShadowPosition(String shadowPosition) {
        this.shadowPosition = shadowPosition;
    }

    public String getChangeInPakistan() {
        return changeInPakistan;
    }

    public void setChangeInPakistan(String changeInPakistan) {
        this.changeInPakistan = changeInPakistan;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getPerformTask() {
        return performTask;
    }

    public void setPerformTask(String performTask) {
        this.performTask = performTask;
    }

    public String getAbideLaws() {
        return abideLaws;
    }

    public void setAbideLaws(String abideLaws) {
        this.abideLaws = abideLaws;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
