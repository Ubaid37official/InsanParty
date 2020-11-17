package rehanfoundation.app.insanparty.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import rehanfoundation.app.insanparty.R;
import rehanfoundation.app.insanparty.model.LoginResponse;
import rehanfoundation.app.insanparty.retrofitUtil.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginActivity extends Activity {

    Button login;
    EditText email, password;
    TextView forgotPassword;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        progressDialog = new ProgressDialog(getApplicationContext());
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

        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().userLogin(Email,Password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (loginResponse.isStatus()==true){
                    Toast.makeText(getApplicationContext(),"Logged In",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),loginResponse.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }



}







