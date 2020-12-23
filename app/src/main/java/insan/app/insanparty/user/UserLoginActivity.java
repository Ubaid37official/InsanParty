package rehanfoundation.app.insanparty.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import rehanfoundation.app.insanparty.MainActivity;
import rehanfoundation.app.insanparty.MemberListActivity;
import rehanfoundation.app.insanparty.R;
import rehanfoundation.app.insanparty.model.login.MDLogin;
import rehanfoundation.app.insanparty.model.login.User;
import rehanfoundation.app.insanparty.retrofitUtil.RetrofitClient;
import rehanfoundation.app.insanparty.retrofitpkg.RetroServices;
import rehanfoundation.app.insanparty.retrofitpkg.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginActivity extends Activity {

    Button login;
    EditText email, password;
    TextView forgotPassword;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);

        login = (Button) findViewById(R.id.login);


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
        }else {
            RetroServices service = RetrofitClientInstance.getApiClient().create(RetroServices.class);
            Call<MDLogin> call = service.userLogin(email.getText().toString(), password.getText().toString());
            call.enqueue(new Callback<MDLogin>() {

                @Override
                public void onResponse(Call<MDLogin> call, Response<MDLogin> response) {
                    if (response.isSuccessful()){
                        MDLogin mdRegister = response.body();
                        boolean status = mdRegister.getStatus();
                        String message = mdRegister.getMessage();

                        dialog.dismiss();

                        if (status == true){
//                        startActivity(new Intent(UserLoginActivity.this, UserHomeActivity.class));
                            SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
                            editor.putString("cat", "user");
                            editor.putString("id", mdRegister.getUser().getId()+"");
                            editor.apply();
                            startActivity(new Intent(UserLoginActivity.this, MemberListActivity.class));
                            finishAffinity();
                        }
                        if (status == false){
                            Toast.makeText(UserLoginActivity.this, ""+ message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<MDLogin> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    Log.e("exception", t.toString());
                }
            });
        }
    }



}







