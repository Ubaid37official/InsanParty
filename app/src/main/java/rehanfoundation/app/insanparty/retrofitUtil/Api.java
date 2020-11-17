package rehanfoundation.app.insanparty.retrofitUtil;

import okhttp3.ResponseBody;
import rehanfoundation.app.insanparty.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("user/login")
    Call<LoginResponse> userLogin(
            @Field("email")String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("member/login")
    Call<ResponseBody> memberLogin(
            @Field("email")String email,
            @Field("password") String password
    );

}
