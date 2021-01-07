package insan.app.insanparty.member;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import rehanfoundation.app.insanparty.MainActivity;
import rehanfoundation.app.insanparty.R;
import rehanfoundation.app.insanparty.model.LoginResponse;
import rehanfoundation.app.insanparty.model.Member;
import insan.app.insanparty.model.login.MDLogin;
import rehanfoundation.app.insanparty.model.member_login.MDMemberLogin;
import rehanfoundation.app.insanparty.retrofitUtil.RetrofitClient;
import rehanfoundation.app.insanparty.retrofitpkg.RetroServices;
import rehanfoundation.app.insanparty.retrofitpkg.RetrofitClientInstance;
import rehanfoundation.app.insanparty.user.UserHomeActivity;
import rehanfoundation.app.insanparty.user.UserLoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberLoginActivity extends Activity {

    private EditText email, password;
    private  TextView forgotPassword;
    private Button login;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberlogin);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);

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
        dialog.show();
        final String Email = email.getText().toString().trim();
        final String Password = password.getText().toString().trim();


        if(Email.isEmpty()){
            email.setError("Email is Required");
            email.requestFocus();
            dialog.dismiss();
            return;
        }
        if(Password.isEmpty()){
            password.setError("Password is Required");
            password.requestFocus();
            dialog.dismiss();
            return;
        }

        RetroServices service = RetrofitClientInstance.getApiClient().create(RetroServices.class);
        Call<MDMemberLogin> call = service.memberLogin(email.getText().toString(), password.getText().toString());
        call.enqueue(new Callback<MDMemberLogin>() {

            @Override
            public void onResponse(Call<MDMemberLogin> call, Response<MDMemberLogin> response) {
                if (response.isSuccessful()){
                    MDMemberLogin mdRegister = response.body();
                    boolean status = mdRegister.getStatus();
                    String message = mdRegister.getMessage();

                    dialog.dismiss();

                    if (status == true){
                        startActivity(new Intent(MemberLoginActivity.this, rehanfoundation.app.insanparty.member.MemberHomeActivity.class));
                    }
                    if (status == false){
                        Toast.makeText(MemberLoginActivity.this, ""+ message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MDMemberLogin> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Log.e("exception", t.toString());
            }
        });
    }
}
