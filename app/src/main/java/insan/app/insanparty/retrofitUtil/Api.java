package rehanfoundation.app.insanparty.retrofitUtil;

import rehanfoundation.app.insanparty.model.Member;
import rehanfoundation.app.insanparty.model.login.User;
import rehanfoundation.app.insanparty.model.memberlist.MDMember;
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
