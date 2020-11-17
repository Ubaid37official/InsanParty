package rehanfoundation.app.insanparty.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import rehanfoundation.app.insanparty.R;
import rehanfoundation.app.insanparty.model.LoginResponse;
import rehanfoundation.app.insanparty.model.Member;
import rehanfoundation.app.insanparty.retrofitUtil.RetrofitClient;
import rehanfoundation.app.insanparty.user.UserHomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberLoginActivity extends Activity {

    private EditText email, password;
    private  TextView forgotPassword;
    private Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberlogin);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        forgotPassword = (TextView)findViewById(R.id.forgotPassword);

        login = (Button)findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateInputs();


            }
        });

    }

    private void validateInputs(){
        final String Email = email.getText().toString().trim();
        final String Password = password.getText().toString().trim();


        if(Email.isEmpty()){
            email.setError("Email is Required");
            email.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            password.setError("Password is Required");
            password.requestFocus();
            return;
        }

        Call<Member> call = RetrofitClient.getInstance().getApi().memberLogin(Email,Password);

        call.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Member member = response.body();
                if (member.isStatus()==true){
                    Toast.makeText(getApplicationContext(),"Logged In",Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(getApplicationContext(),member.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {

            }
        });
    }




}
