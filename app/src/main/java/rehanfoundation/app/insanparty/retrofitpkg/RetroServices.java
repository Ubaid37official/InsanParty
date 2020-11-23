package rehanfoundation.app.insanparty.retrofitpkg;

import okhttp3.MultipartBody;
import rehanfoundation.app.insanparty.model.Member;
import rehanfoundation.app.insanparty.model.login.MDLogin;
import rehanfoundation.app.insanparty.model.login.User;
import rehanfoundation.app.insanparty.model.member_login.MDMemberLogin;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetroServices {

    @FormUrlEncoded
    @Headers({
            "Accept: application/json",
            "X-Requested-With: XMLHttpRequest"})
    @POST("user/login")
    Call<MDLogin> userLogin(
            @Field("email")String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @Headers({
            "Accept: application/json",
            "X-Requested-With: XMLHttpRequest"})
    @POST("member/login")
    Call<MDMemberLogin> memberLogin(
            @Field("email")String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @Headers({
            "Accept: application/json",
            "X-Requested-With: XMLHttpRequest"})
    @POST("member/register")
    Call<MDMemberLogin> memberRegister(
            @Field("name")String name,
            @Field("email")String email,
            @Field("password") String password,
            @Field("father_name") String father_name,
            @Field("cnic") String cnic,
            @Field("phone") String phone,
            @Field("social_links") String social_links,
            @Field("dob") String dob,
            @Field("qualification") String qualification,
            @Field("city_country") String city_country,
            @Field("address") String address,
            @Field("consistency") String consistency,
            @Field("political_interest") String political_interest,
            @Field("shadow_position") String shadow_position,
            @Field("work_time") String work_time,
            @Field("perform_task") String perform_task,
            @Field("abide_laws") String abide_laws,
            @Field("political_background") String political_background,
            @Field("passion") String passion,
            @Field("abilities") String abilities,
            @Field("change_in_pakistan") String change_in_pakistan
    );

    @FormUrlEncoded
    @Headers({
            "Accept: application/json",
            "X-Requested-With: XMLHttpRequest"})
    @POST("user/register")
    Call<MDLogin> userRegister(
            @Field("name")String name,
            @Field("email")String email,
            @Field("password") String password,
            @Field("phone") String phone,
            @Field("dob") String dob,
            @Field("gender") String gender,
            @Field("education") String education,
            @Field("profession") String profession);
}
