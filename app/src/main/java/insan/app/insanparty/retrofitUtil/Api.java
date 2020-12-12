package insan.app.insanparty.retrofitUtil;

import insan.app.insanparty.model.Member;
import insan.app.insanparty.model.login.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("user/login")
    Call<User> userLogin(
            @Field("email")String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("member/login")
    Call<Member> memberLogin(
            @Field("email")String email,
            @Field("password") String password
    );

}
